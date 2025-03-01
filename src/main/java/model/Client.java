package model;

/**
 * Clasa care reprezintă un client.
 */
public class Client {
    private int id;
    private String name;
    private String address;
    private String email;
    private int age;

    /**
     * Constructor care initializează un obiect Client cu toate atributele.
     * @param id ID-ul clientului
     * @param name Numele clientului
     * @param address Adresa clientului
     * @param email Adresa de email a clientului
     * @param age Vârsta clientului
     */
    public Client(int id, String name, String address, String email, int age) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.age = age;
    }

    /**
     * Constructor care initializează un obiect Client fără ID.
     * @param name Numele clientului
     * @param address Adresa clientului
     * @param email Adresa de email a clientului
     * @param age Vârsta clientului
     */
    public Client(String name, String address, String email, int age) {
        super();
        this.name = name;
        this.address = address;
        this.email = email;
        this.age = age;
    }

    /**
     * Constructor fără parametri.
     */
    public Client() {
    }

    /**
     * Returnează ID-ul clientului.
     * @return ID-ul clientului
     */
    public int getId() {
        return id;
    }

    /**
     * Setează ID-ul clientului.
     * @param id ID-ul clientului
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returnează numele clientului.
     * @return Numele clientului
     */
    public String getName() {
        return name;
    }

    /**
     * Returnează adresa clientului.
     * @return Adresa clientului
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returnează vârsta clientului.
     * @return Vârsta clientului
     */
    public int getAge() {
        return age;
    }

    /**
     * Returnează adresa de email a clientului.
     * @return Adresa de email a clientului
     */
    public String getEmail() {
        return email;
    }

    /**
     * Override la metoda toString pentru a afișa informațiile despre client.
     * @return Reprezentarea sub formă de șir de caractere a obiectului Client
     */
    @Override
    public String toString() {
        return "Client [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + ", age=" + age
                + "]";
    }

    /**
     * Setează numele clientului.
     * @param name Numele clientului
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setează adresa clientului.
     * @param address Adresa clientului
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Setează adresa de email a clientului.
     * @param email Adresa de email a clientului
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Setează vârsta clientului.
     * @param age Vârsta clientului
     */
    public void setAge(int age) {
        this.age = age;
    }
}
