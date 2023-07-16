package com.lawencon.lmssanto.constant;

public enum UserRole {

	SUPERADMIN("SPADM", "Super Admin"), TEACHER("TCHR", "Teacher"), STUDENT("STDN", "Student");
	
	public final String roleCode;
	public final String roleName;

	UserRole(String roleCode, String roleName) {
		this.roleCode = roleCode;
		this.roleName = roleName;
	}

}
