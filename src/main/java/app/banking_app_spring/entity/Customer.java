package app.banking_app_spring.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="customers")
public class Customer {

    // ---------- Fields ----------

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(nullable = false)
        private String firstName;

        @Column(nullable = false)
        private String lastName;

        @Column(nullable = false, unique = true)
        private String phoneNumber;

        @Column(nullable = false, unique = true)
        private String email;

        @OneToMany(
                mappedBy = "owner"
        )
        private List<Account> accounts = new ArrayList<>();

    // ---------- Constructors ----------

        protected Customer() {}

        public Customer(
                String firstName,
                String lastName,
                String phoneNumber,
                String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
            this.email = email;
        }

    // ---------- Utility methods ----------

        // helper method for rel(Customer X Account)
        public void addAccount(Account account) {
            account.setOwner(this);
            accounts.add(account);
        }

    // ---------- Getters ----------

        public long getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getEmail() {
            return email;
        }

        public List<Account> getAccounts() {
            return accounts;
        }

    // ---------- Setters ----------

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public void setEmail(String email) {
            this.email = email;
        }
}
