package edu.kh.todolist.run;

import edu.kh.todolist.view.ToDoListView;

public class ToDoListRun {
	public static void main(String[] args) {
		// 프로젝트 흐름
		// Run <> View <> Service <> DAO <> File

		System.out.println("=====프로그램 실행======");

		ToDoListView view = new ToDoListView();

		view.startView();

	}
}