package com.spark.psi.base.internal.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Node<T> {
	
	T entity;
	
	/**
	 * ���ֱ���¼��б�
	 */
	protected List<Node<T>> children = new ArrayList<Node<T>>();
	/**
	 * �����������ڵ�
	 */
	protected List<Node<T>> descendants = new ArrayList<Node<T>>();
	/**
	 * ���ڵ�
	 */
	protected Node<T> parent;
	/**
	 * ���Ƚڵ�
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
		//֪ͨ�����ϼ�����dept
		informParentAdd(child);
		//����dept���ϼ��ڵ��б�
		child.updateParentAdd(this);
	}
	
	/**
	 * ֪ͨ�����ϼ��������б�������dept�Լ�dept����������
	 * @param child
	 */
	private void informParentAdd(Node<T> child){
		for (Node<T> ancestor : ancestors) {
			//֪ͨ�Լ������Ƚڵ��������б�������dept
			ancestor.descendants.add(child);
			//֪ͨ�Լ������Ƚڵ��������б�������dept������ڵ�
			ancestor.descendants.addAll(child.descendants);
		}
	}
	
	/**
	 * �����ϼ��ڵ㣨����ֱ���ϼ����������Ƚڵ�
	 * @param dept
	 */
	private void updateParentAdd(Node<T> parent){
		//�޸��ϼ��ڵ�
		this.parent = parent;
		//�����Լ������Ƚڵ��б�
		this.ancestors.clear();
		this.ancestors.add(parent);
		this.ancestors.addAll(parent.ancestors);
		//������������ڵ���ϼ��ڵ��б�
		for (Node<T> descendant : descendants) {
			descendant.ancestors.clear();
			descendant.ancestors.add(this);
			descendant.ancestors.addAll(this.ancestors);
		}		
	}
	
	/**
	 * �Ƴ�һ���ڵ�
	 * @param child
	 */
	public void removeChild(Node<T> child){
		//ֱ���ϼ��Ƴ����б�
		this.children.remove(child);
		//ֱ���ϼ��Ƴ������б�
		this.descendants.remove(child);
		this.descendants.removeAll(child.descendants);
		//�����Ƚڵ������ڵ��б����Ƴ�dept
		informParentRemove(child);
		//��dept�����Ƚڵ��б����Ƴ���ǰ�ڵ�
		child.updateParentRemove(this);
	}
	
	/**
	 * �����¼��б�
	 * ֪ͨ�������Ƚڵ�����¼��б�
	 * @param child
	 */
	private void informParentRemove(Node<T> child){
		//ֱ���ϼ��������Ƴ������б�
		for (Node<T> ancestor : ancestors) {
			ancestor.descendants.remove(child);
			ancestor.descendants.removeAll(child.descendants);
		}
	}
	
	/**
	 * �����ϼ��б�
	 * @param parent
	 */
	private void updateParentRemove(Node<T> parent){
		//�޸��Լ����ϼ�Ϊnull
		this.parent = null;
		//���Լ�������������б����Ƴ��Լ�������	}
		for (Node<T> descendant : descendants) {
			descendant.ancestors.removeAll(this.ancestors);
		}
		//�޸��Լ�������Ϊnull
		this.ancestors.remove(parent);
		this.ancestors.removeAll(parent.ancestors);
	}
	
	/**
	 * ����
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
