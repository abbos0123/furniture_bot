package uz.project.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "description")
    private String description;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "second_phone_number")
    private String secondPhoneNumber;

    @OneToOne
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private Card card;

    @Column(name = "workPlace")
    private String workPlace;

    @Column(name = "position")
    private String position;

    @Enumerated(EnumType.STRING)
    private Language language;

    @Column(name = "work_experience")
    private float workExperience;

    @Column(name = "salary")
    private double salary;

    @OneToOne
    @JoinColumn(name = "password_image", referencedColumnName = "id")
    private FileStorage passportImage;

    @OneToOne
    @JoinColumn(name = "password_back_image", referencedColumnName = "id")
    private FileStorage passwordBackImage;

    @OneToOne
    @JoinColumn(name = "password_and_user_image", referencedColumnName = "id")
    private FileStorage passwordAndUserImage;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "active_orders",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")

    )
    private List<Product> activeOrders;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "completed_orders",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> completedOrders;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "wishlist",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> wishList;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "active_basket_for_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> activeBasket;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_name", referencedColumnName = "name")}
    )
    private Set<Role> roles;

    public User() {
    }

    public User(String username, String password, String name, String surname, String description, String phoneNumber, String secondPhoneNumber, uz.project.models.Card card, String workPlace, String position, float workExperience, double salary, FileStorage passportImage, FileStorage passwordBackImage, FileStorage passwordAndUserImage, List<Product> activeOrders, List<Product> completedOrders, List<Product> wishList, List<Product> activeBasket) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.secondPhoneNumber = secondPhoneNumber;
        this.card = card;
        this.workPlace = workPlace;
        this.position = position;
        this.workExperience = workExperience;
        this.salary = salary;
        this.passportImage = passportImage;
        this.passwordBackImage = passwordBackImage;
        this.passwordAndUserImage = passwordAndUserImage;
        this.activeOrders = activeOrders;
        this.completedOrders = completedOrders;
        this.wishList = wishList;
        this.activeBasket = activeBasket;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSecondPhoneNumber() {
        return secondPhoneNumber;
    }

    public void setSecondPhoneNumber(String secondPhoneNumber) {
        this.secondPhoneNumber = secondPhoneNumber;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public float getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(float workExperience) {
        this.workExperience = workExperience;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public FileStorage getPassportImage() {
        return passportImage;
    }

    public void setPassportImage(FileStorage passportImage) {
        this.passportImage = passportImage;
    }

    public FileStorage getPasswordBackImage() {
        return passwordBackImage;
    }

    public void setPasswordBackImage(FileStorage passwordBackImage) {
        this.passwordBackImage = passwordBackImage;
    }

    public FileStorage getPasswordAndUserImage() {
        return passwordAndUserImage;
    }

    public void setPasswordAndUserImage(FileStorage passwordAndUserImage) {
        this.passwordAndUserImage = passwordAndUserImage;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public List<Product> getActiveOrders() {
        return activeOrders;
    }

    public void setActiveOrders(List<Product> activeOrders) {
        this.activeOrders = activeOrders;
    }

    public boolean addActiveOrders(Product product) {
        if (this.activeOrders == null) {
            activeOrders = new ArrayList<>();
        }
        if (product != null) {
            activeOrders.add(product);
            return true;
        }
        return false;
    }


    public boolean removeProductFromActiveOrder(Product product){
        if (activeOrders == null)
            return false;

        return activeOrders.remove(product);
    }

    public List<Product> getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(List<Product> completedOrders) {
        this.completedOrders = completedOrders;
    }


    public boolean addCompletedOrders(Product product) {
        if (this.completedOrders == null) {
            completedOrders = new ArrayList<>();
        }
        if (product != null) {
            completedOrders.add(product);
            return true;
        }
        return false;
    }


    public boolean removeProductFromCompletedOrders(Product product) {
        if (completedOrders == null)
            return false;

        return completedOrders.remove(product);
    }

    public List<Product> getWishList() {
        return wishList;
    }

    public void setWishList(List<Product> wishList) {
        this.wishList = wishList;
    }

    public boolean addToWithListOrders(Product product) {
        if (this.wishList == null) {
            wishList = new ArrayList<>();
        }
        if (product != null) {
            wishList.add(product);
            return true;
        }
        return false;
    }

    public boolean removeProductFromWishList(Product product) {
        if (wishList == null)
            return false;

        return wishList.remove(product);
    }

    public List<Product> getActiveBasket() {
        return activeBasket;
    }

    public void setActiveBasket(List<Product> activeBasket) {
        this.activeBasket = activeBasket;
    }

    public boolean addToActiveBasket(Product product) {
        if (this.activeBasket == null) {
            activeBasket = new ArrayList<>();
        }
        if (product != null) {
            activeBasket.add(product);
            return true;
        }
        return false;
    }

    public boolean removeProductFromActiveBasket(Product product) {
        if (activeBasket == null)
            return false;

        return activeBasket.remove(product);
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", description='" + description + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", secondPhoneNumber='" + secondPhoneNumber + '\'' +
                ", card=" + card +
                ", workPlace='" + workPlace + '\'' +
                ", position='" + position + '\'' +
                ", workExperience=" + workExperience +
                ", salary=" + salary +
                ", passportImage=" + passportImage +
                ", passwordBackImage=" + passwordBackImage +
                ", passwordAndUserImage=" + passwordAndUserImage +
                ", activeOrders=" + activeOrders +
                ", completedOrders=" + completedOrders +
                ", wishList=" + wishList +
                ", activeBasket=" + activeBasket +
                '}';
    }
}
