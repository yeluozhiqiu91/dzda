package com.igool.util;


public class MyEnum {

	public static enum CategoryEnum{
		警员(0),
		协警(1),
		交警App(2),
		微信(3),
		支付宝(4),
		行车记录仪(5),
		交警官网(6),
		高德(7),
		行车记录仪二代(8),
		_122电话(11),
		行车记录仪二代_其他(18),
		公交随手拍(19),
		其它2(20)
		;
		private Integer type;
		CategoryEnum(Integer type){
			this.type = type;
		}
		public static String getName(Integer type){
			for(CategoryEnum categoryEnum : CategoryEnum.values()){
				if(categoryEnum.getType().equals(type)){
					return categoryEnum.name();
				}
			}
			return null;
		}

		public static Integer getValue(String name){
			for(CategoryEnum categoryEnum : CategoryEnum.values()){
				if(categoryEnum.name().equals(name)){
					return categoryEnum.getType();
				}
			}
			return null;
		}
		
		public Integer getType() {
			return type;
		}
		public void setType(Integer type) {
			this.type = type;
		}
	}
	
	public static enum UploadType{
		图片(1),
		视频(2),
		图片和视频(3),
		录音(4),
		电话(5)
		;
		private Integer type;
		UploadType(Integer type){
			this.type = type;
		}
		public static String getName(Integer type){
			for(UploadType uploadType : UploadType.values()){
				if(uploadType.getType().equals(type)){
					return uploadType.name();
				}
			}
			return null;
		}
		public Integer getType() {
			return type;
		}
		public void setType(Integer type) {
			this.type = type;
		}
	}

	/**
	 * 待审核->审核通过
	 */
	public static enum IncidentStatusEnum{
		待检测("1"),
		待审核("0"),
		审核不通过("2"),
		;
		private String status;

        IncidentStatusEnum(String status){

			this.status = status;
		}

		public String getStatus() {
			return status;
		}

		public static String getStatusStr(String code){
			for(IncidentStatusEnum incidentStatusEnum : IncidentStatusEnum.values()){
				if(incidentStatusEnum.getStatus().equals(code)){
					return incidentStatusEnum.name();
				}
			}
			return "";
		}
	}

	/**
	 * 待审核->待检测->检测通过->交警已采纳
     */
	public static enum CheckStatusEnum{
		待审核("0", "待录入"),
		待检测("1", "待审核"),
		预审不通过("2", "录入不通过"),
		检测通过("6", "审核通过"),
		检测不通过("7", "审核不予通过"),
		交警已采纳("8", "交警已采纳"),
		交警未采纳("9", "交警未采纳"),
		失效("4", "失效"),
		正在审核中("19", "正在审核中"),
		正在检测中("20", "正在检测中"),
		;
		private String status;

		// 昆明显示名称
		private String kmView;

		CheckStatusEnum(String status, String kmView){
			this.status = status;
			this.kmView = kmView;
		}
		
		public String getStatus() {
			return status;
		}

		public String getKmView() {
			return kmView;
		}

		public static String getStatusStr(String code){
			for(CheckStatusEnum checkStatusEnum : CheckStatusEnum.values()){
				if(checkStatusEnum.getStatus().equals(code)){
					return checkStatusEnum.name();
				}
			}
			return "";
		}

		public static String getKmView(String code) {
			for(CheckStatusEnum checkStatusEnum : CheckStatusEnum.values()){
				if(checkStatusEnum.getStatus().equals(code)){
					return checkStatusEnum.getKmView();
				}
			}
			return "";
		}
	}
	
	public static enum GradeEnum{
		全国(0),
		省(1),
		市(2),
		区(3);
		private Integer code;
		GradeEnum(Integer code){
			this.code = code;
		}
		public Integer getCode() {
			return code;
		}
		public void setCode(Integer code) {
			this.code = code;
		}
	}

	public static enum CheckAction{
		当前(1),
		上一个(2),
		下一个(3);
		private Integer code;
		private CheckAction(Integer code){
			this.code = code;
		}
		public Integer getCode() {
			return code;
		}
	}
	
	public static enum Hpzl{
		大型汽车("01","大型汽车", "黄"),
		小型汽车("02","小型汽车", "蓝"),
		轻便摩托车("08","轻便摩托车", "蓝"),
		两轮或三轮摩托车("07","两、三轮摩托车", "黄"),
		农用运输车("13","农用运输车", "黄"),
		外籍汽车("06","外籍汽车", "黑"),
		领馆汽车("04","领馆汽车", "黑"),
		使馆汽车("03","使馆汽车", "黑"),
		使馆摩托车("09","使馆摩托车", "黑"),
		领馆摩托车("10","领馆摩托车", "黑"),
		境外摩托车("11","境外摩托车", "黑"),
		外籍摩托车("12","外籍摩托车", "黑"),
		港澳入出境车("05","港澳入出境车", "黑"),
		拖拉机("14","拖拉机", "绿"),
		挂车("15","挂车", "黄"),
		教练汽车("16","教练汽车", "黄"),
		警用汽车("23","警用汽车", "白")
		;
		
		private String code;
		private String view;
		private String color;
		
		private Hpzl(String code, String view, String color) {
			this.code = code;
			this.view = view;
			this.color = color;
		}
		
		public static String getHpzlView(String code){
			Hpzl[] values = Hpzl.values();
			for (Hpzl hpzl : values) {
				if(hpzl.getCode().equals(code))
					return hpzl.getView();
			}
			
			return code;
		}

		public static Hpzl getHpzlByName(String name){
			if(name == null || name.trim().equals("")) {
				return null;
			}
			for (Hpzl hpzl : Hpzl.values()) {
				if(hpzl.name().equals(name)) {
					return hpzl;
				}
			}

			return null;
		}

		public static Hpzl getHpzlByCode(String code) {
			if(code == null || code.trim().equals("")) {
				return null;
			}
			for (Hpzl hpzl : Hpzl.values()) {
				if(hpzl.getCode().equals(code)) {
					return hpzl;
				}
			}
			return null;
		}
		
		public String getCode() {
			return code;
		}
		
		public String getView() {
			return view;
		}

		public String getColor() {
			return color;
		}
	}
	
	public static enum ImageType{
		举报图片(1),
		视频截图(2),
		视频封面(3);
		private Integer type;
		private ImageType(Integer type) {
			this.type = type;
		}
		public Integer getType() {
			return type;
		}
		public void setType(Integer type) {
			this.type = type;
		}
	}

	public static enum EOfferImageLoc{
		NULL,/** TODO @author 45度C @date 2015/02/04 15:58 产品需求，枚举序号从1开始 **/
		CQQJ/*车前全景*/,
		CQJJ/*车前近景*/,
		CHQJ/*车后全景*/,
		DTJT/*地图截图*/,
		DEFAULT/*默认图片*/
	}

	public static enum ProvinceEnum{
		鄂("鄂","42"),
		沪("沪","31"),
		苏("苏","32"),
		浙("浙","33"),
		皖("皖","34"),
		鲁("鲁","37"),
		粤("粤","44"),
		赣("赣","36"),
		京("京","11"),
		藏("藏","54"),
		川("川","51"),
		甘("甘","62"),
		桂("桂","45"),
		贵("贵","52"),
		黑("黑","23"),
		吉("吉","22"),
		冀("冀","13"),
		津("津","12"),
		晋("晋","14"),
		辽("辽","21"),
		蒙("蒙","15"),
		闽("闽","35"),
		宁("宁","32"),
		青("青","63"),
		琼("琼","46"),
		陕("陕","61"),
		湘("湘","43"),
		新("新","65"),
		渝("渝","50"),
		豫("豫","41"),
		云("云","53"),
		使("使",""),
		;
		private String value ;
		private String provinceCode;
		ProvinceEnum(String value, String provinceCode){
			this.value = value;
			this.provinceCode = provinceCode;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getProvinceCode(){
			return provinceCode;
		}

		public static String getValueByProvinceCode(String provinceCode){
			for(ProvinceEnum provinceEnum : ProvinceEnum.values()){
				if(provinceCode.equals(provinceEnum.getProvinceCode())){
					return provinceEnum.getValue();
				}
			}
			return null;
		}
	}

	public static enum  ActionEnum{
		审核通过("1"),
		审核不通过("2"),
		检测通过("3"),
		检测不通过("4")
		;
		private String value;
		ActionEnum(String value){
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	public static enum CheckAdvice{
		无效资料("1"),
		车号无法识别("2"),
		举报违法行为不明确("3"),
		举报证据不足("4"),
		四类违法举报范围之外("5"),
		现场有民警执法("6");

		private String code;

		private CheckAdvice(String code){
			this.code = code;
		}

		public String getCode() {
			return code;
		}

		public static CheckAdvice getCheckAdvice(String code){
			CheckAdvice[] checkAdvices = CheckAdvice.values();

			for(CheckAdvice checkAdvice:checkAdvices){
				if(checkAdvice.code.equals(code)){
					return checkAdvice;
				}
			}

			return null;
		}

		public static String
		getCheckAdviceCode(String text){
			CheckAdvice[] checkAdvices = CheckAdvice.values();

			for(CheckAdvice checkAdvice:checkAdvices){
				if(checkAdvice.name().equals(text)){
					return checkAdvice.code;
				}
			}


			return null;
		}
	}

	public static enum TypeEnum{
		联图接口(0),
		用户修改(1),
		用户新增(2),
		严管路段(3)
		;
		private Integer type;

		TypeEnum(Integer type) {
			this.type = type;
		}

		public Integer getType() {
			return type;
		}

		public static String getTypeEnumName(Integer type){
			for(TypeEnum typeEnum : TypeEnum.values()){
				if(typeEnum.getType().equals(type)){
					return typeEnum.name();
				}
			}
			return "";
		}
	}

	public static enum RunModeEnum{
		测试("test"),
		生产("product"),
		;
		private String runMode;

		RunModeEnum(String runMode) {
			this.runMode = runMode;
		}

		public String getRunMode() {
			return runMode;
		}

	}

	public static enum UserType{
		普通用户(0),
		vip用户(1),
		城管(2),
		警员(3)
		;
		private Integer type;
		UserType(Integer type){
			this.type = type;
		}

		public Integer getType() {
			return type;
		}

		public static String getUserTypeName (Integer UserType){
			for(UserType userType : MyEnum.UserType.values()){
				if(userType.type.equals(UserType)){
					return userType.name();
				}
			}
			return "";
		}
	}

	public static enum DlldStatusEnum{
		初始(0),
		默认查回(1),
		用户修改(2),
		严管路段(3)
		;
		private Integer status;

		DlldStatusEnum(Integer status) {
			this.status = status;
		}

		public Integer getStatus() {
			return status;
		}
	}

	public static enum DataTypeEnum{
		违法举报(1),
		事件上报(2),
		车辆监控(3)
		;
		private Integer status;

		DataTypeEnum(Integer status) {
			this.status = status;
		}

		public Integer getStatus() {
			return status;
		}
	}
	public static enum DriverStatusEnum{
		有效(1),
		失效(2),
		;
		private Integer status;

		DriverStatusEnum(Integer status) {
			this.status = status;
		}

		public Integer getStatus() {
			return status;
		}
	}

	public static enum DataSubtypeEnum{
		事故(1),
		施工(2),
		拥堵(3),
		积水(4),
		封路(5),
		管制(6),
		;
		private Integer status;

		DataSubtypeEnum(Integer status) {
			this.status = status;
		}

		public Integer getStatus() {
			return status;
		}
	}

	public static enum ResourceType{
		菜单资源(0),
		功能资源(1),
		;
		private Integer type;
 		ResourceType (Integer type){
 			this.type = type;
		}

		public Integer getType() {
			return type;
		}
	}

	public static enum WaterPressTextLoc{
		右上角(1),
		左上角(2),
		左下角(3),
		右下角(4)
		;
		private Integer loc;
		WaterPressTextLoc(Integer loc){
			this.loc = loc;
		}

		public Integer getLoc() {
			return loc;
		}
	}

	public static enum ExportedStatus{
		已导出("0"),
		未导出("1");
		private String status;
		ExportedStatus(String status){
			this.status = status;
		}
		public String getStatus(){
			return this.status;
		}
		public static String getExportedName(String status){
			for(ExportedStatus e :MyEnum.ExportedStatus.values()){
				if(e.status.equals(status)){
					return e.name();
				}
			}
			return "";
		}

	}

}
