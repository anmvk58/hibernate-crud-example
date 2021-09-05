package org.vti.backend;

import org.vti.entity.Department;

import java.util.List;

public interface IDepartmentRepository {

    //Hàm lấy ra toàn bộ bản ghi trong bảng department
    List<Department> getAllDepartments();

    //Hàm lấy ra một Department dựa vào ID
    Department getDepartmentByID(short id);

    //Hàm lấy ra một Department dựa vào name
    Department getDepartmentByName(String name);

    //Hàm khởi insert 1 Department vào DB
    void createDepartment(Department department);

    //Hàm update 1 Department trong DB truyền id, new name
    void updateDepartment(short id, String newName);

    //Hàm update 1 Department theo object Department
    void updateDepartment(Department department);

    //Hàm xóa 1 Department theo id
    void deleteDepartment(short id);

    //Hàm check xem đã tồn tại Department với ID truyền vào hay chưa
    boolean isDepartmentExistsByID(short id);

    //Hàm check xem đã tồn tại Department với name truyền vào hay chưa
    boolean isDepartmentExistsByName(String name);
}
