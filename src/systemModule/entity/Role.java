package systemModule.entity;

public class Role {
	private Integer roleNumb;
	private Integer roleName;
	public Integer getRoleNumb() {
		return roleNumb;
	}
	public void setRoleNumb(Integer roleNumb) {
		this.roleNumb = roleNumb;
	}
	public Integer getRoleName() {
		return roleName;
	}
	public void setRoleName(Integer roleName) {
		this.roleName = roleName;
	}
	public Role() {
		super();
	}
	public Role(Integer roleNumb, Integer roleName) {
		super();
		this.roleNumb = roleNumb;
		this.roleName = roleName;
	}
	@Override
	public String toString() {
		return "Role [roleNumb=" + roleNumb + ", roleName=" + roleName + "]";
	}
	
}
