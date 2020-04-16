package com.wtxy.familyeducation.user;

/**
 * @Author: maxiaohu
 * @Date: 2020/4/11
 * @Describe:
 */
public class UserInfo {
    /**
     *  管理员
     */
 public static final int ACCOUNT_TYPE_MANAGER = 0;
    /**
     * 教师
      */
  public static final int ACCOUNT_TYPE_TEACHER = 1;
    /**
     *  学生
     */
  public static final int ACCOUNT_TYPE_STUDENT = 2;
    /**
     *  家长
     */
  public static final int ACCOUNT_TYPE_PARENT = 3;

    /**
     *  当前用户角色
     */
  private int currentUserType;
    /**
     *  教师信息
     */
  private TeachInfo teachInfo = new TeachInfo();
    /**
     *  学生信息
     */
  private StudentInfo studentInfo = new StudentInfo();
    /**
     *  家长信息
     */
  private ParentInfo parentInfo = new ParentInfo();

  public String getUserTypeName(){
      switch (currentUserType){
          case ACCOUNT_TYPE_MANAGER:
              return "管理员";
          case ACCOUNT_TYPE_PARENT:
              return "家长";
          case ACCOUNT_TYPE_STUDENT:
              return "学生";
          case ACCOUNT_TYPE_TEACHER:
              return "教师";
      }
      return "管理员";
  }

  public String getUserName(){
      switch (currentUserType){
          case ACCOUNT_TYPE_MANAGER:
              return "管理员";
          case ACCOUNT_TYPE_PARENT:
              return parentInfo.parent_name;
          case ACCOUNT_TYPE_STUDENT:
              return studentInfo.student_name;
          case ACCOUNT_TYPE_TEACHER:
              return teachInfo.teacher_name;
      }
      return "管理员";
  }

    public int getCurrentUserType() {
        return currentUserType;
    }

    public void setCurrentUserType(int currentUserType) {
        this.currentUserType = currentUserType;
    }

    public TeachInfo getTeachInfo() {
        return teachInfo;
    }

    public void setTeachInfo(TeachInfo teachInfo) {
        this.teachInfo = teachInfo;
    }

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    }

    public ParentInfo getParentInfo() {
        return parentInfo;
    }

    public void setParentInfo(ParentInfo parentInfo) {
        this.parentInfo = parentInfo;
    }
}
