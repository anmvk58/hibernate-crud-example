package org.vti.frontend;

import org.vti.backend.DepartmentRepository;
import org.vti.backend.IDepartmentRepository;
import org.vti.entity.Department;

import java.util.List;

public class Program {
    public static void main(String[] args) {
        IDepartmentRepository iDepartmentRepository = new DepartmentRepository();

        layDanhSachDepartment(iDepartmentRepository);

        layDepartmentTheoId(iDepartmentRepository, (short) 1);

        layDepartmentTheoName(iDepartmentRepository, "Marketting");

        ghiDepartmentVaoDB(iDepartmentRepository);

        upDateDepartmentByParameter(iDepartmentRepository, (short) 1, "Apple");

        upDateDepartmentByObject(iDepartmentRepository);

        deleteDepartmentById(iDepartmentRepository, (short) 4);

        ifExistsDepartmentById(iDepartmentRepository, (short) 3);

        ifExistsDepartmentByName(iDepartmentRepository, "Marketting");
    }

    public static void layDanhSachDepartment(IDepartmentRepository iDepartmentRepository){
        List<Department> departments = iDepartmentRepository.getAllDepartments();

        System.out.println("----------- Lấy danh sách Department -----------");
        for (Department d: departments) {
            System.out.println(d.toString());
        }

        System.out.println("------------------------------------------------\n\n");
    }

    public static void layDepartmentTheoId(IDepartmentRepository iDepartmentRepository, short id){
        Department department = iDepartmentRepository.getDepartmentByID(id);

        System.out.println("------------ Lấy Department theo ID ------------");

        System.out.println(department.toString());

        System.out.println("------------------------------------------------\n\n");
    }

    public static void layDepartmentTheoName(IDepartmentRepository iDepartmentRepository, String name){
        Department department = iDepartmentRepository.getDepartmentByName(name);

        System.out.println("----------- Lấy Department theo name -----------");

        System.out.println(department.toString());

        System.out.println("------------------------------------------------\n\n");
    }

    public static void ghiDepartmentVaoDB(IDepartmentRepository iDepartmentRepository){
        Department department = new Department();
        department.setName("Development");

        iDepartmentRepository.createDepartment(department);

        System.out.println("----------- Insert dữ liệu thành công: -----------");

        //Sử dụng hàm tìm kiếm theo tên để in bản ghi mới được insert vào !
        Department department2 = iDepartmentRepository.getDepartmentByName("Development");
        System.out.println(department2.toString());

        System.out.println("------------------------------------------------\n\n");
    }

    public static void upDateDepartmentByParameter(IDepartmentRepository iDepartmentRepository, short id,  String newName){
        iDepartmentRepository.updateDepartment(id, newName);

        System.out.println("----------- Update dữ liệu thành công: -----------");

        //Sử dụng hàm tìm kiếm theo id để in bản ghi mới được update vào !
        Department department = iDepartmentRepository.getDepartmentByID(id);
        System.out.println(department.toString());

        System.out.println("------------------------------------------------\n\n");
    }

    public static void upDateDepartmentByObject(IDepartmentRepository iDepartmentRepository){
        Department department = new Department();
        department.setId((short) 1);
        department.setName("Mirosoft");
        iDepartmentRepository.updateDepartment(department);

        System.out.println("----------- Update dữ liệu thành công: -----------");

        //Sử dụng hàm tìm kiếm theo id để in bản ghi mới được update vào !
        Department department2 = iDepartmentRepository.getDepartmentByID((short) 1);
        System.out.println(department2.toString());

        System.out.println("------------------------------------------------\n\n");
    }

    public static void deleteDepartmentById(IDepartmentRepository iDepartmentRepository, Short id){
        iDepartmentRepository.deleteDepartment(id);

        System.out.println("----------- Delete dữ liệu thành công -----------");

        System.out.println("------------------------------------------------\n\n");
    }

    public static void ifExistsDepartmentById(IDepartmentRepository iDepartmentRepository, Short id){
        System.out.println("----------- Kiểm tra với ID = " + id + "-----------");
        if(iDepartmentRepository.isDepartmentExistsByID(id)) {
            System.out.println("Đã tồn tại");
        } else {
            System.out.println("Chưa tồn tại");
        }
        System.out.println("------------------------------------------------\n\n");
    }

    public static void ifExistsDepartmentByName(IDepartmentRepository iDepartmentRepository, String name){
        System.out.println("----------- Kiểm tra với tên = " + name + "-----------");
        if(iDepartmentRepository.isDepartmentExistsByName(name)) {
            System.out.println("Đã tồn tại");
        } else {
            System.out.println("Chưa tồn tại");
        }
        System.out.println("------------------------------------------------\n\n");
    }
}
