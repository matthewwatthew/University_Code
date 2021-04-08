/**
 * Homework 1
 * Festus Aguai , fna2s
 *
 * ...
 */

public class Photograph{

    /**
     * The caption of the photograph
     */
    final private String CAPTION;

    /**
     * The file name of the photograph
     */
    final private String FILENAME;


    /**
     * This is a Photograph constructor
     *
     * @param caption The user's input to be set to CAPTION
     * @param filename The user's input to be set to FILENAME
     * */
    public Photograph(String caption, String filename) {
        this.CAPTION = caption;
        this.FILENAME = filename;
    }

    /**
     * This method is the accessor(getter) for filename
     */
    public String getFilename() {
        return FILENAME;
    }

    /**
     * This method is the accessor(getter) for caption
     */
    public String getCaption() {
        return CAPTION;
    }


    /**
     * This method is the mutator(setter) for FILENAME
     * @param filename The parameter the user inputs to be set to filename
     */
    public void setFilename(String filename) {
        filename = this.FILENAME;
    }

    /**
     * This method is the mutator(setter) for CAPTION
     * @param caption The parameter the user inputs to be set to CAPTION
     */
    public void setCaption(String caption) {
        caption = this.FILENAME;
    }

    /**
     * This returns TRUE if the Photograph object passed to equals() with caption
     * and filename strings match(are equal to) the caption and filename.
     * If strings of the current Photograph object; otherwise, return false
     *
     * @param o The object passed to be checked for equality
     */
    public boolean equals(Object o) {
        if(o!= null && o instanceof Photograph) {
            Photograph hello = (Photograph) o;
            if (this.FILENAME == hello.FILENAME
                    && this.CAPTION == hello.CAPTION)
            {
                return true;
            }
            else
                return false;
        } else return false;
    }

    /**
     * This method gives a formal output for
     * the FILENAME and CAPTION
     */
    public String toString() {
        return ("File name: " + this.FILENAME + ".\n" +  "Caption: " + this.CAPTION);
    }


    /**
     * This is the main method for testing two methods from
     * the Photograph class
     */
    public static void main(String[] args) {
        Photograph a = new Photograph("I was in monaco", "Monaco.jpeg");
        Photograph b = new Photograph("I was in france", "France.jpeg");
        Photograph a_repeat = new Photograph("I was in monaco", "Monaco.jpeg");

        System.out.println(a.equals(b));
        System.out.println(a.equals(a_repeat));

        System.out.print(b.toString());

    }
}