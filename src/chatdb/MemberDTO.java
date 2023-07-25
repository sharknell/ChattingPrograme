package chatdb;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class MemberDTO {
    public String id;
    public String name;
    public String password;
    public String phonenumber;
    private int countrycode;
    private Date reg_date;
    private String RRN;

    // Getter and Setter methods for id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter and Setter methods for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter methods for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter methods for phonenumber
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    // Getter and Setter methods for countrycode
    public int getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(int countrycode) {
        this.countrycode = countrycode;
    }

    // Getter and Setter methods for reg_date
    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    // Getter and Setter methods for RRN
    public String getRRN() {
        return RRN;
    }

    public void setRRN(String RRN) {
        this.RRN = RRN;
    }

	public MemberDTO(String id, String name, String phonenumber) {
		this.id = id;
		this.name = name;
		this.phonenumber = phonenumber;
	}

}
