package vo;

import java.util.Date;

//import org.json.JSONException;
//import org.json.JSONObject;

public class Person {
    private String firstName;
    private String lastName;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private int zipCode;
    private String country;
    private Date timestamp; 

    // builder pattern
    private Person(PersonBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.address1 = builder.address1;
        this.address2 = builder.address2;
        this.city = builder.city;
        this.state = builder.state;
        this.zipCode = builder.zipCode;
        this.country = builder.country;
        this.timestamp = builder.timestamp;
    }
    
    public String getFullName() {
        return firstName;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }
    
    public Date getTimestamp() {
        return timestamp;
    }

    
    public static class PersonBuilder {
        private String firstName;
        private String lastName;
        private String address1;
        private String address2;
        private String city;
        private String state;
        private int zipCode;
        private String country;
        private Date timestamp; 
       
        public Person build() {
            return new Person(this);
        }
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void setAddress1(String address1) {
            this.address1 = address1;
        }

        public void setAddress2(String address2) {
            this.address2 = address2;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setState(String state) {
            this.state = state;
        }

        public void setZipCode(int zipCode) {
            this.zipCode = zipCode;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public void setTimestamp(Date timestamp) {
            this.timestamp = timestamp;
        }

    
    }



/*    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("first_name", firstName);
            obj.put("last_name", lastName);
            obj.put("address1", address1);
            obj.put("address2", address2);
            obj.put("city", city);
            obj.put("state", state);
            obj.put("zipCode", zipCode);
            obj.put("country", country);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }*/

}
