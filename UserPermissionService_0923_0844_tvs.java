// 代码生成时间: 2025-09-23 08:44:06
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户权限管理系统，负责用户权限的管理和验证。
 */
@ManagedBean(name = "userPermissionService")
@SessionScoped
public class UserPermissionService {

    // 存储用户权限的列表
    private List<String> userPermissions;

    // 构造函数
    public UserPermissionService() {
        this.userPermissions = new ArrayList<>();
    }

    /**
     * 添加用户权限。
     * @param permission 要添加的权限。
     */
    public void addPermission(String permission) {
        if (!userPermissions.contains(permission)) {
            userPermissions.add(permission);
        }
    }

    /**
     * 移除用户权限。
     * @param permission 要移除的权限。
     */
    public void removePermission(String permission) {
        userPermissions.remove(permission);
    }

    /**
     * 检查用户是否拥有特定权限。
     * @param permission 要检查的权限。
     * @return 如果用户拥有该权限，则返回true，否则返回false。
     */
    public boolean hasPermission(String permission) {
        return userPermissions.contains(permission);
    }

    /**
     * 获取所有用户权限。
     * @return 返回用户权限列表。
     */
    public List<String> getUserPermissions() {
        return userPermissions;
    }

    // Getter和Setter方法
    public List<String> getUserPermissionsList() {
        return userPermissions;
    }

    public void setUserPermissionsList(List<String> userPermissions) {
        this.userPermissions = userPermissions;
    }
}
