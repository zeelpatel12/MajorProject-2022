package com.cybage.ecommerce.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {
                
                @Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                private long id;
                
                @Column(name = "first_name")
                private String firstName;

                @Column(name = "last_name")
                private String lastName;
                
                @Column(name = "email_id")
                private String emailId;
                
                @Column (name = "panNumber")
                private String panNumber;
                
                @Column (name = "gstNumber")
                private String gstNumber;
                
                @Column (name = "AddressId")
                private String AddressId;
                
                @Column (name = "accountNumber")
                private String accountNumber;
                

                public Employee() {
                                
                }
                
                public Employee(String firstName, String lastName, String emailId,String panNumber, String gstNumber, String addressId, String accountNumber) {
                                super();
                                this.firstName = firstName;
                                this.lastName = lastName;
                                this.emailId = emailId;
                                this.panNumber = panNumber;
                                this.gstNumber = gstNumber;
                                this.AddressId = addressId;
                                this.accountNumber = accountNumber;
                }


                public long getId() {
                                return id;
                }
                public void setId(long id) {
                                this.id = id;
                }
                public String getFirstName() {
                                return firstName;
                }
                public void setFirstName(String firstName) {
                                this.firstName = firstName;
                }
                public String getLastName() {
                                return lastName;
                }
                public void setLastName(String lastName) {
                                this.lastName = lastName;
                }
                public String getEmailId() {
                                return emailId;
                }
                public void setEmailId(String emailId) {
                                this.emailId = emailId;
                }
                public String getPanNumber() {
                                return panNumber;
                }

                public void setPanNumber(String panNumber) {
                                this.panNumber = panNumber;
                }

                public String getGstNumber() {
                                return gstNumber;
                }

                public void setGstNumber(String gstNumber) {
                                this.gstNumber = gstNumber;
                }

                public String getAddressId() {
                                return AddressId;
                }

                public void setAddressId(String addressId) {
                                AddressId = addressId;
                }

                public String getAccountNumber() {
                                return accountNumber;
                }

                public void setAccountNumber(String accountNumber) {
                                this.accountNumber = accountNumber;
                }

}
