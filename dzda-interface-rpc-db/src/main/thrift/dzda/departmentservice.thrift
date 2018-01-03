include "dzda/departmentinfo.thrift"
namespace java com.igool.rpc.db.service.thrift

	service DepartmentService {
	    list<departmentinfo.Department> getDepartmentList(),
	    void addDepartment(1:departmentinfo.Department department),
	    departmentinfo.Department findDepartmentById(1:i32 departmentId),
	    void updateDepartment(1:departmentinfo.Department department),
	    i32 getChildrenCount(1:i32 departmentId),
	    i32 getPersonCount(1:i32 departmentId),
	    void deleteDepartmentById(1:i32 departmentId)
	}