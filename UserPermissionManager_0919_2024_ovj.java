// 代码生成时间: 2025-09-19 20:24:08
package com.example.security;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户权限管理系统
 *
 * 这个类提供了用户权限管理的基本功能，包括添加、删除和验证用户权限。
 */
public class UserPermissionManager {

    private static final Map<String, String> userPermissions;

    // 初始化用户权限映射
    static {
        userPermissions = new HashMap<>();
        userPermissions.put("user1", "READ_WRITE");
        userPermissions.put("user2", "READ_ONLY");
    }

    /**
     * 添加或更新用户权限
     *
     * @param username 用户名
     * @param permission 权限
     */
    public void addUserPermission(String username, String permission) {
        if (username == null || permission == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username or permission", ""));
            return;
        }
        userPermissions.put(username, permission);
    }

    /**
     * 删除用户权限
     *
     * @param username 用户名
     */
    public void removeUserPermission(String username) {
        if (username == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username", ""));
            return;
        }
        userPermissions.remove(username);
    }

    /**
     * 验证用户权限
     *
     * @param username 用户名
     * @param requiredPermission 所需权限
     * @return 是否具有所需权限
     */
    public boolean hasPermission(String username, String requiredPermission) {
        if (username == null || requiredPermission == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username or required permission", ""));
            return false;
        }
        String userPermission = userPermissions.get(username);
        return userPermission != null && userPermission.contains(requiredPermission);
    }
}
