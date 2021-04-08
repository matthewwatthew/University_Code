/**
 * Homework 1
 * Festus Aguai , fna2s
 * ...
 */


import java.util.ArrayList;

public class PhotoLibrary{
    /**
     * The name of the PhotoLibrary
     */
    private String name;

    /**
     * The ID of the PhotoLibrary
     */
    private int id;

    /**
     * An ArrayList filled with photographs
     */
    private ArrayList <Photograph> photoList;

    public PhotoLibrary(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /**
     * This method is the accessor(getter) for name
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method is the accessor(getter) for id
     */
    public int getID() {
        return this.id;
    }

    /**
     * This method is the mutator(setter) for name
     * @param name The name the user inputs to be set to name
     */
    public void setName(String name) {
        name = this.name;
    }

    /**
     * This method is the mutator(setter) for id
     * @param id The id the user inputs to be set to id
     */
    public void setId(int id) {
        id = this.id;
    }

    /**
     *This method adds the photograph p to the list of current objects's photos
     * feed if and only if it was not already in that list. Returns TRUE
     * if the photograph was added; return false if it was not added.
     *
     * @param p A photograph object to add to the current list of photos
     */
    public boolean addPhoto(Photograph p) {
        if (p!=null && !this.photoList.contains(p)) {
            this.photoList.add(p);
            return true;
        }
        else return false;
    }

    /**
     * This method returns true if the current object has p in its list of photos.
     * Otherwise return false.
     *
     * @param p Object used to check whether it is in the photoList
     */
    public boolean hasPhoto(Photograph p) {
        if(photoList.contains(p)) {
            return true;
        }
        else return false;
    }

    /**
     * If Photograph p is in the current PhotoLibrary object's list
     * of photographs, this method removes p from the current object's list.
     * Return true if the Photograph was removed or false
     * if it was not there.
     *
     *@param p Object used to check whether it is in the photoList
     */
    public boolean erasePhoto(Photograph p) {
        if(this.photoList.contains(p)) {
            this.photoList.remove(p);
            return true;
        }
        else return false;
    }

    /**This method returns the number of Photographs the current object has taken(in photos)
     */
    public int numPhotographs() {
        return this.photoList.size();
    }

    /**
     * This method returns true if the current Photolibrary's object id value is equal
     * to the id value of the PhotoLibrary object passed to equals()
     * otherwise return false
     *
     * @param o The object being passed for equality
     */
    public boolean equals(Object o) {
        if(o!= null && o instanceof PhotoLibrary) {
            PhotoLibrary new_P = (PhotoLibrary) o;
            if (this.id == new_P.id)
            {
                return true;
            }
            else
                return false;
        } else return false;
    }

    /**
     * This method is a means to print out a PhotoLibrary object.
     *Generate a String that shows the values of
     *the fields name, id, and photos.
     *Any reasonable implementation of this is acceptable
     *
     */
    public String toString() {
        return ("Name: " + name + "\nID: " + id + "\nPhotos: " + photoList);
    }


    /**
     *This method returns an ArrayList<Photograph> of the photos that both PhotoLibrary a and PhotoLibrary b have posted to their feeds.
     *Uses the equals method of the Photograph class
     *to determine if two Photograph objects represent the same photograph.
     *
     *@param a A photolibrary used for comparison
     *@param b A photolibrary used for comparison
     */
    public static ArrayList<Photograph> commonPhotos(PhotoLibrary a, PhotoLibrary b){
        ArrayList<Photograph> Libraries_ab = new ArrayList<Photograph>();

        for (int i = 0; i<a.photoList.size(); i++) {
            if(!Libraries_ab.contains(a.photoList.get(i))) {
                Libraries_ab.add(a.photoList.get(i));
            }
        }
        //
        for(int i = 0; i<b.photoList.size(); i++) {
            if(!b.photoList.get(i).equals(Libraries_ab.get(i))) {
                Libraries_ab.remove(b.photoList.get(i));
            }
        }
        return Libraries_ab;
    }

    /**
     * This method returns a measure of how similar the photo feeds are between PhotoLibrary a and PhotoLibrary b,
     * in terms of a numerical value between 0 and 1. If either PhotoLibrary does
     not have any photos, the result is 0.0. Otherwise, it is the number of commonPhotos to
     both libraries divided by smaller of the number of photos in a’s feed and the number of
     photos in b’s feed.
     Reminder: Java respects types, so the integer division of 3 / 4 gives integer 0, while
     including a float or double in the division 3 / 4.0 gives 0.75.
     *
     *@param a A PhotoLibrary being used for comparison
     *@param b A PhotoLibrary being used for comparison
     */
    public static double similarity(PhotoLibrary a, PhotoLibrary b) {
        if (a.photoList.size() < b.photoList.size()) {
            return (double) commonPhotos(a, b).size() / a.photoList.size();
        }
        else if (a.photoList.size() > b.photoList.size()) {
            return (double) commonPhotos(a, b).size() / b.photoList.size();
        }

        return (double)(a.photoList.size()/b.photoList.size());
    }

    public static void main(String[] args) {
        //Method testing
        PhotoLibrary a = new PhotoLibrary("Places I have been", 4576);
        PhotoLibrary b = new PhotoLibrary("Places", 5029);
        Photograph add_photograph = new Photograph("Remember", "rem.jpeg");

        a.addPhoto(add_photograph);

        System.out.println(a.photoList);
    }
}
