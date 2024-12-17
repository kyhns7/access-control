package com.kyhns7.rbac.entity.dto;

import com.kyhns7.rbac.entity.domain.MenuEntity;
import lombok.Data;

/**
 * 菜单<br>
 * <a href="https://pure-admin.cn/pages/routerMenu/">具体参考</a>
 * @author kyhns7
 * @email kyhns7@outlook.com
 * @date 2023-07-03 06:53:50
 */
@Data
public class MenuDto {

	/**
	 * id
	 */
	private Long id;
	/**
	 * 上级id
	 */
	private Long parentId;
	/**
	 * 类型
	 * @see MenuEntity#TYPE_MENU 菜单
	 * @see MenuEntity#TYPE_IFRAME iframe
	 * @see MenuEntity#TYPE_EXTERNAL_LINK 外链
	 * @see MenuEntity#TYPE_BUTTON 按钮
	 */
	private Integer type;
	/**
	 * 标识,如果是菜单则与路径一致,如果是按钮则是权限标识
	 */
	private String symbol;
	/**
	 * 路由路径
	 */
	private String path;
	/**
	 * 路由名称
	 */
	private String name;
	/**
	 * 路由重定向
	 */
	private String redirect;

	/**
	 * 按需加载需要展示的页面
	 */
	private String component;

	/**
	 * 菜单名称
	 */
	private String title;

	/**
	 * 菜单图标
	 */
	private String icon;

	/**
	 * 菜单名称右侧的额外图标
	 */
	private String extraIcon;

	/**
	 * 菜单排序
	 */
	private Integer rank;

	/**
	 * 是否显示该菜单
	 */
	private Boolean showLink;

	/**
	 * 是否显示父级菜单
	 */
	private Boolean showParent;
	/**
	 * 是否缓存该路由页面（开启后，会保存该页面的整体状态，刷新后会清空状态）
	 */
	private Boolean keepAlive;

	/**
	 * 需要内嵌的iframe链接地址
	 */
	private String frameSrc;

	/**
	 * 内嵌的iframe页面是否开启首次加载动画
	 */
	private Boolean frameLoading;

	/**
	 * 当前页面进场动画
	 */
	private String enterTransition;

	/**
	 * 当前页面离场动画
	 */
	private String leaveTransition;

	/**
	 * 当前菜单名称或自定义信息禁止添加到标签页
	 */
	private Boolean hiddenTag;

	/**
	 * 显示在标签页的最大数量
	 */
	private Integer dynamicLevel;

	/**
	 * 将某个菜单激活
	 */
	private String activePath;

}
