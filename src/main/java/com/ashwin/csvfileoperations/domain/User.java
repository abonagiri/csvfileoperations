    package com.ashwin.csvfileoperations.domain;

    import com.ashwin.csvfileoperations.util.CustomEmailValidator;

    import javax.persistence.*;
    import javax.validation.constraints.NotEmpty;

    @Entity
    public class User {

        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Long id;

        @Column
        @NotEmpty(message = "{user.firstname.notempty}")
        private String firstname;

        @Column
        @NotEmpty(message = "{user.lastname.notempty}")
        private String lastname;

        @Column
        @NotEmpty
        @CustomEmailValidator
        //@Email(message = "{user.email.notempty}")
        private String email;

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Long getId() {
            return id;
        }
    }
