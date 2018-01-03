package com.igool.ssp.web.util;



public enum ExceptionEnum  {

	已经没有更多存储("E003"),

	解压文件失败("E010"),
	解压文件IO异常("E011"),
	未找到文件("E012"),
	不支持的压缩格式("E013"),

	业务类型异常("E014"),

	登录过时("0009"),

	数据库异常("E001"),
	参数异常("E002"),
	查询不到相关数据("E003"),
	权限不足("E004"),
	用户没有权限("E005"),
	
	//用户账户相关,
	用户不存在("E010"),
	用户名或密码不能为空("E011"),
	密码错误("E020"),
	密码错误次数超过5次限制("E021"),
	账号状态异常("E022"),
	创建的用户账号已存在("E023"),
	输入的验证码有误("E024"),
	没有登录后台权限("E025"),
	没有登录移动端权限("E026"),
	终端不支持登录("E027"),
	账号不存在("E028"),
	root登录账号不允许修改("E029"),
	
	//accessToken
	token校验失败("E030"),
	token已过期("E031"),
	token不允许为空("E032"),
	
	//用户权限
	无任何权限("E040"),
	当前用户无该功能使用权限("E041"),
	无当前角色编码配置("E042"),

	//案件相关
	创建案件失败("E050"),
	修改案件超过时间("E051"),
	案件非本人创建("E052"),
	没有删除案件权限("E053"),
	新增或更新车信息失败("E054"),
	
	//应用版本更新
	没有相应的应用版本信息("E060"),
	
	业务数据不能为空("E070"),
	
	//短信服务
	短信模板的构已存在("E080"),

	//影像采集
	该影像已采集("E090"),
	该业务类型有多个版本("E091"),
	该业务类型没有配置("E092"),
	没有该业务类型("093"),
	没有该影像("E094"),
	该流水号的业务类型为空("E095"),
	该影像数据node缺失("E096"),
	生成node失败("E097"),


	该模块名称已被使用("E110"),
	此模块不存在("E111"),

	//部门
	部门ID异常("120"),
	部门参数异常("121"),
	部门列表异常("122"),
	该部门被其它部门调用("123"),
	部门迭代异常("124"),
	部门名称异常("125"),

	//流水批次
	没有该批次信息("E130"),
	该批次没有登记流水("E131"),
	该批次没有签收信息("E132"),
	批次不能为空("E133"),
	该批次不是已签收状态("E134"),
	该批次不是签收中或已登记状态("E135"),
	该箱号已存在("E136"),
	该批次没有登记信息("E137"),
	没有此状态("E138"),


	//角色
	角色名称不能为空("E140"),
	角色名称已被使用("E141"),
	没有此角色("E142"),

	//流水信息表
	流水号异常("E150"),
	流水信息列表异常("E151"),
	该档案已出库("E152"),
	该档案已签收("E153"),
	没有该流水信息("E154"),
	时间格式化异常("E155"),
	此箱号没有流水信息("E156"),
	该档案已封箱("E157"),
	此箱号没有签收信息("E158"),
	该档案已被登记("E159"),

	//登记表
	批次号异常("E160"),
	登记人异常("E161"),
	当日批次号已达上限("E162"),
	该批次没有登记此流水("E163"),

	//公告异常
	公告操作者异常("E170"),
	公告对象参数异常("E171"),
	公告ID异常("E172"),
	公告不存在("E173"),

	//公告异常
	附件操作者异常("E180"),
	附件对象参数异常("E181"),

	//机动车流水号查询接口 
	未查询到对应的机动车信息("E190"),
	创建链接接口超时("E191"),
	调用接口返回超时("E192"),
	调用接口返回出错("E193"),
	违法行为查询("E194"),
	返回包的头信息为空("E195"),
	转换对象出错("E196"),
	返回信息为空("E197"),
	调用接口异常("E198"),

	//扫描仪
	排序号异常("E200"),

	//OSS
	OSS存储异常("E210"),

	//档案
	没有此档案("E220"),

	//业务小类
	业务ID异常("E230"),
	该业务类型为空("E231"),
	该业务有配置不能删除("E232"),

	//业务配置
	没有此业务配置("E240"),

	//同步库
	查询同步库IO异常("E250"),

	该流水没有被登记("F260"),
	档案编号位数不对("F261"),
	档案编号含有非法字符("F262"),
	档案编号范围在1到20之间("F263"),
	档案号段不全("F264"),
    档案编号范围不正确("F265"),

	//未知异常
	其它异常("E1000"),
	请求的接口版本不支持("FFFF"),
	验证失败_不允许调用("E1001"),


	;
	
	private String code;
	
	private ExceptionEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	public static ExceptionEnum toStatus(String code) {
		ExceptionEnum[] values = ExceptionEnum.values();
		
		for (ExceptionEnum exceptionEnum : values) {
			if(exceptionEnum.getCode().equals(code)) {
				return exceptionEnum;
			}
		}
		return null;
	}
}