package model;

/**
 * Clasa care reprezintă o comandă.
 */
public class Order {
    private int id;
    private int quantity;
    private int id_client;
    private int id_product;
    /**
     * Constructor care initializează o comandă cu toate atributele.
     * @param quantity Cantitatea produsului comandat
     * @param id_client ID-ul clientului care a plasat comanda
     * @param id_product ID-ul produsului comandat
     */
    public Order(int quantity, int id_client, int id_product) {
        super();
        this.quantity = quantity;
        this.id_client = id_client;
        this.id_product = id_product;
    }
    /**
     * Returnează ID-ul comenzii.
     * @return ID-ul comenzii
     */
    public int getId() {
        return id;
    }
    /**
     * Setează ID-ul comenzii.
     * @param id ID-ul comenzii
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Setează cantitatea produsului comandat.
     * @param quantity Cantitatea produsului comandat
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /**
     * Returnează ID-ul clientului care a plasat comanda.
     * @return ID-ul clientului care a plasat comanda
     */
    public int getId_client() {
        return id_client;
    }
    /**
     * Returnează ID-ul produsului comandat.
     * @return ID-ul produsului comandat
     */
    public int getId_product() {
        return id_product;
    }
    /**
     * Returnează cantitatea produsului comandat.
     * @return Cantitatea produsului comandat
     */
    public int getQuantity() { return quantity; }
    /**
     * Override la metoda toString pentru a afișa informațiile despre comandă.
     * @return Reprezentarea sub formă de șir de caractere a obiectului Order
     */
    @Override
    public String toString() {
        return "Order [id=" + id + ", quantity=" + quantity + ", id_client=" + id_client + ", id_product=" +
                id_product + "]";
    }
}
