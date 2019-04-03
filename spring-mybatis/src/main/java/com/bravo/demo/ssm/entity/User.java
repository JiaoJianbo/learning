package com.bravo.demo.ssm.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.bravo.demo.ssm.validator.MyConstraint;

//@Entity
public class User implements Serializable {
	private static final long serialVersionUID = -6927279856337686228L;

//	@Id
//	@GeneratedValue
	private String id;
	
	@NotBlank(message = "用户名不能为空")
	@MyConstraint //自定义验证规则
	private String username;

	@NotBlank
	@Length(min = 6, max = 16)
	private String password;
	
	@Past
	private Date birthday;
	
	private Integer age;
	
	private String gender;
	
	private Timestamp lastLogonTime;

	private Timestamp createDate;
	
	private Timestamp updateDate;
	
	public User () {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Timestamp getLastLogonTime() {
		return lastLogonTime;
	}

	public void setLastLogonTime(Timestamp lastLogonTime) {
		this.lastLogonTime = lastLogonTime;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", birthday=" + birthday + ", age=" + age + ", gender="
				+ gender + ", lastLogonTime=" + lastLogonTime + ", createDate=" + createDate + ", updateDate="
				+ updateDate + "]";
	}
}
