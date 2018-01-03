<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="crumbs">
	<span>新增部门</span>
</div>

<div class="box c-form">
	<form action="/updateDepartment" id="myform">
		<input type="hidden" name="departmentId" value="${department.departmentId}">
		<div class="item">
			<div class="txt">部门名称：</div>
			<div class="cnt">
				<input type="text" class="ipt" value="${department.name}" name="name" autocomplete="off">
			</div>
		</div>
		<div class="item">
			<div class="txt">部门代码：</div>
			<div class="cnt">
				<input type="text" class="ipt" value="${department.code}" name="code" autocomplete="off">
			</div>
		</div>
		<c:if test="${department.parentId==-1}">
			<input type="hidden" value="${department.parentId}" name="parentId">
		</c:if>
		<c:if test="${department.parentId!=-1}">
			<div class="item">
				<div class="txt">父级部门：</div>
				<div class="cnt">
					<select name="parentId" class="slt" data-msg="你还未填写父级部门">
						<c:forEach var="d" items="${departmentList}">
							<option value="${d.departmentId}" <c:if test="${d.departmentId==department.parentId}">selected</c:if>>${d.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</c:if>
		<div class="item">
			<div class="txt">排序：</div>
			<div class="cnt">
				<input type="text" class="ipt" value="${department.sort}" name="sort" autocomplete="off">
			</div>
		</div>
		<div class="item">
			<div class="txt">备注：</div>
			<div class="cnt">
				<input type="text" class="ipt" value="${department.remarks}" name="remarks" autocomplete="off">
			</div>
		</div>
		<div class="item">
			<div class="txt">是否生效：</div>
			<div class="cnt cbxs">
				<label><input type="radio" name="status" class="status"  value="1" <c:if test="${department.status==1}">checked</c:if>/>生效</label>
				<label><input type="radio" name="status" class="status" value="0" <c:if test="${department.status==0}">checked</c:if>/>失效</label>
			</div>
		</div>
		<div class="ft">
			<button type="submit" class="ubtn ubtn-primary" id="jsubmit">保存</button>
			<button type="button" class="ubtn ubtn-gray" id="jback">取消</button>
		</div>
	</form>
</div>
<script type="text/javascript">



	!(function($) {
		var _global = {
			init: function() {
				this.bindEvent();
				this.checkForm();
			},
			bindEvent: function() {
				// 取消
				$('#jback').on('click', function() {
					window.history.back(-1);
				})
			},
			checkForm: function() {
				var that = this;
				$('#myform').validator({
					fields: {
						name: '部门名称: required',
						orderNumber: '排序: required;digits',
						remarks:'备注：required',
						status: '是否生效: checked'
					},
					valid : function(form) {
						form.submit();
					}
				});
			}
		}


		_global.init();
	})(jQuery);


</script>