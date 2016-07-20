package com.bruce.model;

	public class CrudUsers{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -8536467008591402343L;


		public CrudUsers(String fname, String lname, String phone, String email ,String picPath) {
			super();
			this.firstname = fname;
			this.lastname = lname;
			this.phone = phone;
			this.email = email;
			this.picPath = picPath;
		}

		public CrudUsers() {
			// TODO Auto-generated constructor stub
		}

		private long id;
		
		private String firstname;
		
		private String lastname;
		
		private String phone;
		
		private String email;

		private String picPath;
		
		private String picPathBig;
		
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

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

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPicPath() {
			return picPath;
		}

		public void setPicPath(String picPath) {
			this.picPath = picPath;
		}

		public String getPicPathBig() {
			return picPathBig;
		}

		public void setPicPathBig(String picPathBig) {
			this.picPathBig = picPathBig;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		@Override
		public String toString() {
			return "CrudUsers [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname+ ", phone="
					+ phone + ", email=" + email + "]";
		}



		public boolean isAccountNonExpired() {
			return true;
		}

		public boolean isAccountNonLocked() {
			return true;
		}

		public boolean isCredentialsNonExpired() {
			return true;
		}




}
