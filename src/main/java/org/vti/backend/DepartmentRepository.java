package org.vti.backend;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.vti.entity.Department;

import java.util.List;

public class DepartmentRepository implements IDepartmentRepository{
    //khai báo thuộc tính kiểu SessionFactory. Do sử dụng kết nối DB nhiều lần
    private SessionFactory sessionFactory;

    //Hàm khởi tạo của đối tượng Department, sử dụng hàm buildSessionFactory()
    public DepartmentRepository() {
        this.sessionFactory = buildSessionFactory();
    }

    //Hàm khởi tạo ra được SessionFactory dựa trên file config
    private SessionFactory buildSessionFactory() {
        // load configuration
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        // add entity
        configuration.addAnnotatedClass(Department.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    public List<Department> getAllDepartments() {
        //Khởi tạo session rỗng
        Session session = null;

        try {
            // Sử dụng sessionFactory để mở Session mới để làm việc
            session = sessionFactory.openSession();

            // Truy vấn lấy về dữ liệu theo cú pháp của hibernate
            Query<Department> query = session.createQuery("FROM Department");

            return query.list();

        } catch (Exception e){
            // In mã lỗi nếu có
            System.out.println(e.getMessage());
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Department getDepartmentByID(short id) {

        Session session = null;

        try {

            // Sử dụng sessionFactory để mở Session mới để làm việc
            session = sessionFactory.openSession();

            // Truy vấn để lấy ra department có id = id được truyền vào
            Department department = session.get(Department.class, id);

            return department;

        } catch (Exception e){
            // In mã lỗi nếu có
            System.out.println(e.getMessage());
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Department getDepartmentByName(String name) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            // truy vấn để lấy ra bản ghi có name = tham số truyền vào
            Query<Department> query = session.createQuery("FROM Department WHERE name = :nameParameter");
            // set parameter
            query.setParameter("nameParameter", name);
            // get result
            Department department = query.uniqueResult();
            return department;
        } catch (Exception e) {
            // In mã lỗi nếu có
            System.out.println(e.getMessage());
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void createDepartment(Department department) {

        Session session = null;

        try {

            // get session
            session = sessionFactory.openSession();
            session.beginTransaction();

            // create
            session.save(department);

            session.getTransaction().commit();
        } catch (Exception e ){
            // Thực hiện Rollback
            session.getTransaction().rollback();

            // In mã lỗi nếu có
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void updateDepartment(short id, String newName) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            // get department
            Department department = (Department) session.load(Department.class, id);
            // update
            department.setName(newName);

            session.getTransaction().commit();

        } catch (Exception e ){
            // Thực hiện Rollback
            session.getTransaction().rollback();

            // In mã lỗi nếu có
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void updateDepartment(Department department) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

            // update
            session.update(department);

            session.getTransaction().commit();
        } catch (Exception e ){
            // Thực hiện Rollback
            session.getTransaction().rollback();

            // In mã lỗi nếu có
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void deleteDepartment(short id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

            // get department
            Department department = (Department) session.load(Department.class, id);

            // delete
            session.delete(department);

            session.getTransaction().commit();

        } catch (Exception e ){
            // Thực hiện Rollback
            session.getTransaction().rollback();

            // In mã lỗi nếu có
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public boolean isDepartmentExistsByID(short id) {
        Department department = getDepartmentByID(id);

        if (department == null) {
            return false;
        }
        return true;
    }

    public boolean isDepartmentExistsByName(String name) {
        Department department = getDepartmentByName(name);

        if (department == null) {
            return false;
        }
        return true;
    }
}
