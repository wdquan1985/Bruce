package com.bruce.model;

	public class Product{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -8536467008591402343L;

		public Product() {
			// TODO Auto-generated constructor stub
		}

		private long id;
		
		/* 品牌 */
		private String brand;
		/*型号*/
		private String model;
		
		private String dmd;
		/* 光机厂商 */
		private String manufacturers;
		/* 流明 */
		private String lumen;
		/* 真实流明 */
		private String realLumen;
		/* 主板方案 */
		private String mainboard;
		/* 运行内存 */
		private String ram;
		/* 存储内存 */
		private String memory;
		/* 售价 */
		private String price;
		/* 大约成本 */
		private String costing;
		/* 销售数量(台/月) */
		private String salesAmount;
		/* 金额/月 */
		private String money;
		/* 梯形校正  */
		private String trapezoidal;
		/* 光源 */
		private String lightSource;

		
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}
		

		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public String getDmd() {
			return dmd;
		}

		public void setDmd(String dmd) {
			this.dmd = dmd;
		}

		public String getManufacturers() {
			return manufacturers;
		}

		public void setManufacturers(String manufacturers) {
			this.manufacturers = manufacturers;
		}

		public String getLumen() {
			return lumen;
		}

		public void setLumen(String lumen) {
			this.lumen = lumen;
		}

		public String getRealLumen() {
			return realLumen;
		}

		public void setRealLumen(String realLumen) {
			this.realLumen = realLumen;
		}

		public String getMainboard() {
			return mainboard;
		}

		public void setMainboard(String mainboard) {
			this.mainboard = mainboard;
		}

		public String getRam() {
			return ram;
		}

		public void setRam(String ram) {
			this.ram = ram;
		}

		public String getMemory() {
			return memory;
		}

		public void setMemory(String memory) {
			this.memory = memory;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public String getCosting() {
			return costing;
		}

		public void setCosting(String costing) {
			this.costing = costing;
		}

		public String getSalesAmount() {
			return salesAmount;
		}

		public void setSalesAmount(String salesAmount) {
			this.salesAmount = salesAmount;
		}

		public String getMoney() {
			return money;
		}

		public void setMoney(String money) {
			this.money = money;
		}

		public String getTrapezoidal() {
			return trapezoidal;
		}

		public void setTrapezoidal(String trapezoidal) {
			this.trapezoidal = trapezoidal;
		}

		public String getLightSource() {
			return lightSource;
		}

		public void setLightSource(String lightSource) {
			this.lightSource = lightSource;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
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
