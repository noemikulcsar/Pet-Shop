package model;

/**
 * Clasa care reprezintă un produs.
 */
public class Product {
    private int id;
    private String name;
    private int stock;

    /**
     * Constructor care initializează un produs cu toate atributele.
     * @param id ID-ul produsului
     * @param name Numele produsului
     * @param stock Cantitatea disponibilă în stoc
     */
    public Product(int id, String name, int stock) {
        super();
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    /**
     * Constructor care initializează un produs fără ID.
     * @param name Numele produsului
     * @param stock Cantitatea disponibilă în stoc
     */
    public Product(String name, int stock) {
        super();
        this.name = name;
        this.stock = stock;
    }

    /**
     * Constructor fără parametri.
     */
    public Product() {
    }

    /**
     * Returnează ID-ul produsului.
     * @return ID-ul produsului
     */
    public int getId() {
        return id;
    }

    /**
     * Setează ID-ul produsului.
     * @param id ID-ul produsului
     */
    public void setId(int id) {
        this.id= id;
    }

    /**
     * Returnează numele produsului.
     * @return Numele produsului
     */
    public String getName() {
        return name;
    }

    /**
     * Returnează cantitatea disponibilă în stoc pentru produs.
     * @return Cantitatea disponibilă în stoc
     */
    public int getStock() { return stock; }

    /**
     * Setează cantitatea disponibilă în stoc pentru produs.
     * @param stock Cantitatea disponibilă în stoc
     */
    public void setStock(int stock) { this.stock = stock; }

    /**
     * Override la metoda toString pentru a afișa informațiile despre produs.
     * @return Reprezentarea sub formă de șir de caractere a obiectului Product
     */
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", stock=" + stock + "]";
    }

    /**
     * Setează ID-ul produsului.
     * @param id_product ID-ul produsului
     */
    public void setId_product(int id_product) {
        this.id = id_product;
    }

    /**
     * Setează numele produsului.
     * @param name Numele produsului
     */
    public void setName(String name) {
        this.name = name;
    }
}
