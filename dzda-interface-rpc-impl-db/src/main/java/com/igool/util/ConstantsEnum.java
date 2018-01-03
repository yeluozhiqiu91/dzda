package com.igool.util;

/**
 * 公共常数
 * <p>
 * RolesResourceType    角色资源类型
 * delStatus            删除状态
 * SigningStatus        批次签收状态
 * imageStatus          影像状态
 */
public class ConstantsEnum {

    /**
     * 角色资源类型
     */
    public static enum RolesResourceType {
        模块(1),
        部门(2),
        其他(3);

        private Integer value;

        private RolesResourceType(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return this.value;
        }

        public static RolesResourceType getResourceTypeByType(Integer type) {
            if (type == null || type.equals("")) {
                return null;
            }
            for (RolesResourceType RolesResourceTypeType : RolesResourceType.values()) {
                if (RolesResourceTypeType.value.equals(type)) {
                    return RolesResourceTypeType;
                }
            }
            return null;
        }

    }

    /**
     * 删除状态类型
     */
    public static enum delStatus {
        已删除(0),
        已启用(1);

        private Integer value;

        private delStatus(Integer val) {
            this.value = val;
        }

        public Integer getValue() {
            return this.value;
        }
    }

    /**
     * 业务类型
     */
    public static enum businessType {
        其他("QT");

        private String value;

        businessType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    /**
     * 影像状态
     */
    public static enum imageStatus {
        未采集(0),
        已采集(1),
        采集中(2);

        private Integer value;

        private imageStatus(Integer val) {
            this.value = val;
        }

        public Integer getValue() {
            return this.value;
        }
    }

    /**
     * 批次签收状态
     */
    public static enum SigningStatus {
        已登记(0),
        已签收(1),
        签收中(2),
        交接中(3),
        已交接(4);

        private Integer value;

        private SigningStatus(Integer val) {
            this.value = val;
        }

        public Integer getValue() {
            return this.value;
        }

        public static SigningStatus getStatusByType(Integer type) {
            if (type == null || type.equals("")) {
                return null;
            }
            for (SigningStatus signingStatus : SigningStatus.values()) {
                if (signingStatus.value.equals(type)) {
                    return signingStatus;
                }
            }
            return null;
        }
    }

    /**
     * 流水交接状态
     */
    public static enum JjStatus {
        A转B登记(1),
        A转B签收(2),
        B转A登记(3),
        B转A签收(4);
        private Integer value;

        private JjStatus(Integer val) {
            this.value = val;
        }

        public Integer getValue() {
            return this.value;
        }

        public static JjStatus getStatusByType(Integer type) {
            if (type == null || type.equals("")) {
                return null;
            }
            for (JjStatus jjStatus : JjStatus.values()) {
                if (jjStatus.value.equals(type)) {
                    return jjStatus;
                }
            }
            return null;
        }
    }

    public static enum Superadmin {
        是超级管理员(1),
        不是超级管理员(0);

        private Integer value;

        private Superadmin(Integer val) {
            this.value = val;
        }

        public Integer getValue() {
            return this.value;
        }
    }


    public static enum UserStatus {
        已删除(0),
        已启用(1);

        private Integer value;

        private UserStatus(Integer val) {
            this.value = val;
        }

        public Integer getValue() {
            return this.value;
        }

        public static UserStatus getStatusByType(Integer type) {
            if (type == null || type.equals("")) {
                return null;
            }
            for (UserStatus userStatus : UserStatus.values()) {
                if (userStatus.value.equals(type)) {
                    return userStatus;
                }
            }
            return null;
        }

    }

    public static enum UserType {
        普通用户(0),
        超级管理员(1);

        private Integer value;

        private UserType(Integer val) {
            this.value = val;
        }

        public Integer getValue() {
            return this.value;
        }

        public static UserType getUserTypeByType(Integer type) {
            if (type == null || type.equals("")) {
                return null;
            }
            for (UserType userType : UserType.values()) {
                if (userType.value.equals(type)) {
                    return userType;
                }
            }
            return null;
        }
    }

    public static enum DepartmentStatus {
        删除(0),
        启用(1);

        private Integer value;

        private DepartmentStatus(Integer val) {
            this.value = val;
        }

        public Integer getValue() {
            return this.value;
        }

        public static DepartmentStatus getDepartmentStatusByType(Integer type) {
            if (type == null || type.equals("")) {
                return null;
            }
            for (DepartmentStatus departmentStatus : DepartmentStatus.values()) {
                if (departmentStatus.value.equals(type)) {
                    return departmentStatus;
                }
            }
            return null;
        }

    }

    public static enum NoParent {
        没有父级(-1);
        private Integer value;

        private NoParent(Integer val) {
            this.value = val;
        }

        public Integer getValue() {
            return this.value;
        }
    }

    public static enum NoticeStatus {
        已下线(0),
        未发布(1),
        已发布(2);

        private Integer value;

        private NoticeStatus(Integer val) {
            this.value = val;
        }

        public Integer getValue() {
            return this.value;
        }
    }

    public static enum FileCkStatus {
        已出库(0),
        未出库(1),
        出库中(2),;

        private Integer value;

        private FileCkStatus(Integer val) {
            this.value = val;
        }

        public Integer getValue() {
            return this.value;
        }
    }

    /**
     * 删除状态类型
     */
    public static enum SigningRelStatus {
        未封箱(0),
            已封箱(1);

        private Integer value;

        private SigningRelStatus(Integer val) {
            this.value = val;
        }

        public Integer getValue() {
            return this.value;
        }

        public static SigningRelStatus getSigningRelStatusByType(Integer type) {
            if (type == null || type.equals("")) {
                return null;
            }
            for (SigningRelStatus value : SigningRelStatus.values()) {
                if (value.value.equals(type)) {
                    return value;
                }
            }
            return null;
        }
    }

    /**
     * 号牌种类
     */
    public static enum Hpzl {
        大型汽车("01"),
        小型汽车("02"),
        使馆汽车("03"),
        领馆汽车("04"),
        境外汽车("05"),
        外籍汽车("06"),
        普通摩托车("07"),
        轻便摩托车("08"),
        使馆摩托车("09"),
        领馆摩托车("10"),
        境外摩托车("11"),
        外籍摩托车("12"),
        低速车("13"),
        拖拉机("14"),
        挂车("15"),
        教练汽车("16"),
        教练摩托车("17"),
        试验汽车("18"),
        试验摩托车("19"),
        临时入境汽车("20"),
        临时入境摩托车("21"),
        临时行驶车("22"),
        警用汽车("23"),
        警用摩托("24"),
        原农机号牌("25"),
        香港入出境车("26"),
        澳门入出境车("27"),
        武警号牌("31"),
        军队号牌("32"),
        无号牌("41"),
        假号牌("42"),
        挪用号牌("43"),
        其他号牌("99"),
        大型新能源汽车("51"),
        小型新能源汽车("52"),;
        private String value;

        private Hpzl(String val) {
            this.value = val;
        }

        public String getValue() {
            return this.value;
        }

        public static Hpzl getHpzlByType(String type) {
            if (type == null || type.equals("")) {
                return null;
            }
            for (Hpzl value : Hpzl.values()) {
                if (value.value.equals(type)) {
                    return value;
                }
            }
            return null;
        }

        public static Hpzl getHpzlByTypeName(String name) {
            if (name == null || name.equals("")) {
                return null;
            }
            for (Hpzl value : Hpzl.values()) {
                if (value.name().equals(name)) {
                    return value;
                }
            }
            return null;
        }
    }

    /**
     * 档案状态
     */
    public static enum FileStatus {
        正常档案(1),
        问题档案(0);

        private Integer value;

        public Integer getValue() {
            return this.value;
        }

        private FileStatus(Integer val) {
            this.value = val;
        }

        public static FileStatus getFileStatusByType(Integer type) {
            if (type == null || type.equals("")) {
                return null;
            }
            for (FileStatus value : FileStatus.values()) {
                if (value.value.equals(type)) {
                    return value;
                }
            }
            return null;
        }
    }

    /**
     * 流水缺失状态
     */
    public static enum QsStatus {
        正常(1),
        流水缺失(0),
        资料缺失(2),
        影像完成(3);

        private Integer value;

        private QsStatus(Integer val) {
            this.value = val;
        }

        public Integer getValue() {
            return this.value;
        }

        public static QsStatus getQsStatusByType(Integer type) {
            if (type == null || type.equals("")) {
                return null;
            }
            for (QsStatus value : QsStatus.values()) {
                if (value.value.equals(type)) {
                    return value;
                }
            }
            return null;
        }
    }

    /**
     * 流水业务状态
     */
    public static enum LsStatus {
        正常("1"),
        嫌疑("2"),
        流水修改("3"),
        合格证待核查("5"),
        嫌疑并合格证核查未通过("6"),
        完成("E"),
        退办("Q"),
        业务未办结("W"),;

        private String value;

        private LsStatus(String val) {
            this.value = val;
        }

        public String getValue() {
            return this.value;
        }

        public static LsStatus getLsStatusByType(String type) {
            if (type == null || type.equals("")) {
                return null;
            }
            for (LsStatus value : LsStatus.values()) {
                if (value.value.equals(type)) {
                    return value;
                }
            }
            return null;
        }
    }

    /**
     * 扫描状态类型
     */
    public static enum isSmStatus {
        不扫描(0),
        扫描(1);

        private Integer value;

        private isSmStatus(Integer val) {
            this.value = val;
        }

        public Integer getValue() {
            return this.value;
        }

        public static isSmStatus getisSmStatusByType(Integer type) {
            if (type == null || type.equals("")) {
                return null;
            }
            for (isSmStatus value : isSmStatus.values()) {
                if (value.value.equals(type)) {
                    return value;
                }
            }
            return null;
        }
    }

    /**
     * 必要资料状态
     */
    public static enum IsNecessity {
        不必要(0),
        必要(1);

        private Integer value;

        private IsNecessity(Integer val) {
            this.value = val;
        }

        public Integer getValue() {
            return this.value;
        }

        public static IsNecessity getIsNecessityByType(Integer type) {
            if (type == null || type.equals("")) {
                return null;
            }
            for (IsNecessity value : IsNecessity.values()) {
                if (value.value.equals(type)) {
                    return value;
                }
            }
            return null;
        }
    }

    /**
     * code种类
     */
    public static enum codeType {
        dabh("dabh"),
        lsh("lsh"),;
        private String value;

        private codeType(String val) {
            this.value = val;
        }

        public String getValue() {
            return this.value;
        }
    }

    /**
     *
     */
    public static enum rwType {
        read("读操作"),
        write("写操作"),
        uploadNoOperate("上传后不能继续操作");
        private String value;

        private rwType(String val) {
            this.value = val;
        }

        public String getValue() {
            return this.value;
        }
    }
    /**
     * 影像操作类型
     */
    public static enum imageType {
        新增("a"),
        删除("d"),
        修改("m"),
        排序更新("o"),
        补全缺失("u")
        ;

        private String value;

        private imageType(String val) {
            this.value = val;
        }

        public String getValue() {
            return this.value;
        }
    }
}
