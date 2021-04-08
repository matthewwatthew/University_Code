import random
import matplotlib.pyplot as plt
from numpy.random import beta as beta_sample
from numpy.random import normal as normal_sample
from numpy import linspace
from scipy.stats import beta
from scipy.stats import norm
from scipy.optimize import minimize, brentq
import operator
import math

# Number of examples per label
m = 1000

# Parameters for Beta distributions
alpha_1, beta_1 = 0, 1
alpha_2, beta_2 = (2/3) * math.pi, math.sqrt(0.5)

# Draw examples and assign labels
x1 = list(normal_sample(alpha_1,beta_1,m))
y1 = [1]*m
# print(sorted(x1))
x2 = list(normal_sample(alpha_2,beta_2,m))
y2 = [-1]*m
# print(sorted(x2))

# Combine positive and negative examples
x = x1 + x2
y = y1 + y2

# Plot the examples
plt.hist([x1,x2], bins=50, color=['r','b'], density=True)

# PDF plot of Beta(x;4,1)
ls1 = linspace(norm.ppf(0.00001, alpha_1, beta_1),norm.ppf(0.99999, alpha_1, beta_1), 100)
plt.plot(ls1, norm.pdf(ls1, alpha_1, beta_1), 'r-', lw=2, alpha=0.6, label=r"$\alpha=0,\beta=1$")

# PDF plot of Beta(x;1,4)
ls2 = linspace(norm.ppf(0.00001, alpha_2, beta_2), norm.ppf(0.99999, alpha_2, beta_2), 100)
plt.plot(ls2, norm.pdf(ls2, alpha_2, beta_2), 'b-', lw=2, alpha=0.6, label=r"$\alpha=2/3pi,\beta=0.5$")

plt.xticks(size=14)
plt.yticks(size=14)
plt.legend(fontsize=14)
# plt.savefig("fig-mix-beta.png")
plt.savefig("fig-mix-beta-samples.png")

def compute_empirical_risk(x, y, b):
  error_counts = 0.0
  for (idx,val) in enumerate(x):
    if (val <= b) and (y[idx] > 0):
      error_counts += 1
    elif (val > b) and (y[idx] < 0):
      error_counts += 1
  return error_counts/len(x)

def find_thresh(x, y, N=1000):
  emp_errs, true_errs = {}, {}
  sorted_x = sorted(x)
  for idx in range(N):
    b = (1.0*idx)/N
    emp_err = compute_empirical_risk(x, y, b)
    true_err = 0.5*norm.cdf(b,alpha_1,beta_1) + 0.5*(1 - norm.cdf(b,alpha_2,beta_2))
    emp_errs[b] = emp_err
    true_errs[b] = true_err
  sorted_emp_errs = sorted(emp_errs.items(), key=operator.itemgetter(1))
  sorted_true_errs = sorted(true_errs.items(), key=operator.itemgetter(1))
  b, emp_err = sorted_emp_errs[0]
  bast, true_err = sorted_true_errs[0]
  return b, emp_err, true_errs[b], bast, true_err

def find_bayes_thresh():
  f = lambda x : norm.pdf(x,alpha_1,beta_1) - norm.pdf(x,alpha_2,beta_2)
  res = brentq(f, -5, 5)
  print(res)
  return res

def compute_bayes_error(b):
  bayes = 0.5*norm.cdf(b,alpha_1,beta_1) + 0.5*(1 - norm.cdf(b,alpha_2,beta_2))

hs, hs_emp_err, hs_true_err, hast, hast_true_err = find_thresh(x, y, N=1000)
print("h_S = {}\n\tEmpirical error L_S(h_S) = {}\n\tTrue error L_D(h_S) = {}\nh^ast={}\n\tTrue_error L_D(h^ast) = {}".format(hs, hs_emp_err, hs_true_err,hast, hast_true_err))