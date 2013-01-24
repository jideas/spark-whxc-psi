package com.spark.psi.base.internal.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Node<T> {
	
	T entity;
	
	/**
	 * 存放直属下级列表
	 */
	protected List<Node<T>> children = new ArrayList<Node<T>>();
	/**
	 * 存放所有子孙节点
	 */
	protected List<Node<T>> descendants = new ArrayList<Node<T>>();
	/**
	 * 父节点
	 */
	protected Node<T> parent;
	/**
	 * 祖先节点
	 */
	protected List<Node<T>> ancestors = new ArrayList<Node<T>>();
	
	public T getEntity(){
		return entity;
	}
	
	public void setEntity(T entity){
		this.entity = entity;
	}
	
	public void addChild(Node<T> child){
		this.children.add(child);
		this.descendants.add(child);
		this.descendants.addAll(child.descendants);
		//通知所有上级增加dept
		informParentAdd(child);
		//更新dept的上级节点列表
		child.updateParentAdd(this);
	}
	
	/**
	 * 通知所有上级在子孙列表中增加dept以及dept的所有子孙
	 * @param child
	 */
	private void informParentAdd(Node<T> child){
		for (Node<T> ancestor : ancestors) {
			//通知自己的祖先节点在子孙列表中增加dept
			ancestor.descendants.add(child);
			//通知自己的祖先节点在子孙列表中增加dept的子孙节点
			ancestor.descendants.addAll(child.descendants);
		}
	}
	
	/**
	 * 更新上级节点（包括直属上级及所有祖先节点
	 * @param dept
	 */
	private void updateParentAdd(Node<T> parent){
		//修改上级节点
		this.parent = parent;
		//更新自己的祖先节点列表
		this.ancestors.clear();
		this.ancestors.add(parent);
		this.ancestors.addAll(parent.ancestors);
		//更新所有子孙节点的上级节点列表
		for (Node<T> descendant : descendants) {
			descendant.ancestors.clear();
			descendant.ancestors.add(this);
			descendant.ancestors.addAll(this.ancestors);
		}		
	}
	
	/**
	 * 移除一个节点
	 * @param child
	 */
	public void removeChild(Node<T> child){
		//直属上级移除子列表
		this.children.remove(child);
		//直属上级移除子孙列表
		this.descendants.remove(child);
		this.descendants.removeAll(child.descendants);
		//在祖先节点的子孙节点列表中移除dept
		informParentRemove(child);
		//在dept的祖先节点列表中移除当前节点
		child.updateParentRemove(this);
	}
	
	/**
	 * 更新下级列表
	 * 通知所有祖先节点更新下级列表
	 * @param child
	 */
	private void informParentRemove(Node<T> child){
		//直属上级的祖先移除子孙列表
		for (Node<T> ancestor : ancestors) {
			ancestor.descendants.remove(child);
			ancestor.descendants.removeAll(child.descendants);
		}
	}
	
	/**
	 * 更新上级列表
	 * @param parent
	 */
	private void updateParentRemove(Node<T> parent){
		//修改自己的上级为null
		this.parent = null;
		//从自己的子孙的祖先列表中移除自己的祖先	}
		for (Node<T> descendant : descendants) {
			descendant.ancestors.removeAll(this.ancestors);
		}
		//修改自己的祖先为null
		this.ancestors.remove(parent);
		this.ancestors.removeAll(parent.ancestors);
	}
	
	/**
	 * 销毁
	 */
	public void destroy(){
		for (int i = this.descendants.size()-1; i > -1; i--) {
			this.descendants.get(i).destroy();
		}
		if(this.parent!=null)
			this.parent.removeChild(this);
		this.ancestors = null;
		this.children = null;
		this.descendants = null;
	}

	public List<Node<T>> getChildNodes() {
		return children;
	}

	public List<Node<T>> getDescendantNodes() {
		return descendants;
	}

	public Node<T> getParentNode() {
		return parent;
	}

	public List<Node<T>> getAncestorNodes() {
		return ancestors;
	}
	
}
