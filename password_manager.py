import os
import pickle
from cryptography.hazmat.backends import default_backend
from cryptography.hazmat.primitives import hashes, hmac
from cryptography.hazmat.primitives.ciphers.aead import AESGCM
from cryptography.hazmat.primitives.kdf.pbkdf2 import PBKDF2HMAC

class PasswordManager:
    MAX_PASSWORD_LEN = 64
    key = ""
    salt = os.urandom(16)
    nonce = salt[0:12]
    def __init__(self, password, data=None, checksum=None):
        self.kvs = {}
        backend = default_backend()

        kdf = PBKDF2HMAC(
            algorithm=hashes.SHA256(),
            length=32,
            salt=self.salt,
            iterations=2000000,
            backend=backend)

        if data is not None:
            if checksum is not None:
                cryptocheck = hashes.Hash(hashes.SHA256(), backend=default_backend())
                cryptocheck.update(bytes(data, 'ascii'))
                cryptocheckbytes = cryptocheck.finalize()
                if cryptocheckbytes != checksum:
                    print(cryptocheck)
                    print(checksum)
                    raise ValueError('Hash of Data and Checksum do not Match')
            self.kvs = pickle.loads(bytes.fromhex(data))
            if self.kvs["Salt"] is None:
                raise ValueError('No Domain entitled: Salt')
            self.key = kdf.derive(bytes(password, 'ascii'))
        else:
            self.kvs["Salt"] = self.salt
            self.key = kdf.derive(bytes(password, 'ascii'))

    def dump(self):
        cryptocheck = hashes.Hash(hashes.SHA256(), backend=default_backend())
        data = pickle.dumps(self.kvs).hex()
        cryptocheck.update(bytes(data, 'ascii'))
        cryptocheckbytes = cryptocheck.finalize()

        return pickle.dumps(self.kvs).hex(), cryptocheckbytes

    def get(self, domain):
        key = self.key
        domain = bytes(domain, 'ascii')
        h = hmac.HMAC(key, hashes.SHA256(), backend=default_backend())
        h.update(domain)
        insertdomain = h.finalize()
        if insertdomain in self.kvs:
            aesgcm = AESGCM(self.key)
            pwstr = aesgcm.decrypt(self.nonce, self.kvs[insertdomain], None)
            return pwstr.decode('ascii')

        return None

    def set(self, domain, password):

        if len(password) > self.MAX_PASSWORD_LEN:
            raise ValueError('Maximum password length exceeded')

        h = hmac.HMAC(self.key, hashes.SHA256(), backend=default_backend())
        domain = bytes(domain, 'ascii')
        h.update(domain)
        insertdomain = h.finalize()

        if insertdomain in self.kvs:
            raise ValueError('Domain Already Exists.')
        aesgcm = AESGCM(self.key)
        password = bytes(password, 'ascii')
        ct = aesgcm.encrypt(self.nonce, password, None)
        self.kvs[insertdomain] = ct

    def remove(self, domain):
        h = hmac.HMAC(self.key, hashes.SHA256(), backend=default_backend())
        domain = bytes(domain, 'ascii')
        h.update(domain)
        insertdomain = h.finalize()

        if insertdomain in self.kvs:
            del self.kvs[insertdomain]
            return True

        return False

    def generate_new(self, domain, desired_len):
        alphabet = ['a', 'b', 'c', 'd', 'e', 'f', 'g',
                         'h', 'i', 'j', 'k', 'l', 'm', 'n',
                         'o', 'p', 'q', 'r', 's', 't', 'u',
                         'v', 'w', 'x', 'y', 'z','0','1','2',
                        '3','4','5','6','7','8','9','A','B','C',
                    'D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z']
        if domain in self.kvs:
            raise ValueError('Domain already in database')
        if desired_len > self.MAX_PASSWORD_LEN:
            raise ValueError('Maximum password length exceeded')
        new_password = ""
        interum = int.from_bytes(self.key, byteorder='big')
        interum2 = str(interum)
        for i in range(desired_len):
            stop = interum2[i] + interum2[i+1]
            go = int(stop) % 62
            new_password += alphabet[go]
        self.set(domain, new_password)

        return new_password
