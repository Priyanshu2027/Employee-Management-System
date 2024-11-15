import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeManagement {

    static void display(ArrayList<Staff> al) {
        System.out.println("\n--------------Staff List---------------\n");
        System.out.println(String.format("%-10s%-15s%-10s%-20s%-10s", "ID", "Name", "Salary", "Contact-No", "Email-Id"));
        for (Staff s : al) {
            System.out.println(String.format("%-5s%-20s%-10s%-15s%-10s", s.id, s.name, s.salary, s.contact_no, s.email_id));
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        int id;
        String name;
        float salary;
        long contact_no;
        String email_id;

        Scanner sc = new Scanner(System.in);
        ArrayList<Staff> al = new ArrayList<Staff>();

        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            f = new File("N:/Java Work Space/Eclipse Programs/Employee Management Tool/src/StaffDataList1.txt");
            if (f.exists()) {
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                al = (ArrayList<Staff>) ois.readObject();
            }

        } catch (Exception exp) {
            System.out.println(exp);
        }
        do {
            System.out.println("\n**Welcome to the Employee Management System*\n");
            System.out.println("1). Add Staff to the Database\n" +
                    "2). Search for Staff\n" +
                    "3). Edit Staff details\n" +
                    "4). Delete Staff Details\n" +
                    "5). Display all Staff working in this company\n" +
                    "6). EXIT\n");
            System.out.println("Enter your choice : ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.println("\nEnter the following details to ADD to the list:\n");
                    System.out.println("Enter ID :");
                    id = sc.nextInt();
                    System.out.println("Enter Name :");
                    name = sc.next();
                    System.out.println("Enter Salary :");
                    salary = sc.nextFloat();
                    System.out.println("Enter Contact No :");
                    contact_no = sc.nextLong();
                    System.out.println("Enter Email-ID :");
                    email_id = sc.next();
                    al.add(new Staff(id, name, salary, contact_no, email_id));
                    display(al);
                    break;

                case 2:
                    System.out.println("Enter the Staff ID to search :");
                    id = sc.nextInt();
                    int i = 0;
                    for (Staff s : al) {
                        if (id == s.id) {
                            System.out.println(s + "\n");
                            i++;
                        }
                    }
                    if (i == 0) {
                        System.out.println("\nStaff Details are not available, Please enter a valid ID!!");
                    }
                    break;

                case 3:
                    System.out.println("\nEnter the Staff ID to EDIT the details");
                    id = sc.nextInt();
                    int j = 0;
                    for (Staff s : al) {
                        if (id == s.id) {
                            j++;
                            do {
                                int ch1 = 0;
                                System.out.println("\nEDIT Staff Details :\n" +
                                        "1). Staff ID\n" +
                                        "2). Name\n" +
                                        "3). Salary\n" +
                                        "4). Contact No.\n" +
                                        "5). Email-ID\n" +
                                        "6). GO BACK\n");
                                System.out.println("Enter your choice : ");
                                ch1 = sc.nextInt();
                                switch (ch1) {
                                    case 1:
                                        System.out.println("\nEnter new Staff ID:");
                                        s.id = sc.nextInt();
                                        System.out.println(s + "\n");
                                        break;

                                    case 2:
                                        System.out.println("Enter new Staff Name:");
                                        s.name = sc.nextLine();
                                        System.out.println(s + "\n");
                                        break;

                                    case 3:
                                        System.out.println("Enter new Staff Salary:");
                                        s.salary = sc.nextFloat();
                                        System.out.println(s + "\n");
                                        break;

                                    case 4:
                                        System.out.println("Enter new Staff Contact No. :");
                                        s.contact_no = sc.nextLong();
                                        System.out.println(s + "\n");
                                        break;

                                    case 5:
                                        System.out.println("Enter new Staff Email-ID :");
                                        s.email_id = sc.next();
                                        System.out.println(s + "\n");
                                        break;

                                    case 6:
                                        j++;
                                        break;

                                    default:
                                        System.out.println("\nEnter a correct choice from the List :");
                                        break;

                                }
                            } while (j == 1);
                        }
                    }
                    if (j == 0) {
                        System.out.println("\nStaff Details are not available, Please enter a valid ID!!");
                    }

                    break;

                case 4:
                    System.out.println("\nEnter Staff ID to DELETE from the Database :");
                    id = sc.nextInt();
                    int k = 0;
                    try {
                        for (Staff s : al) {
                            if (id == s.id) {
                                al.remove(s);
                                display(al);
                                k++;
                            }
                        }
                        if (k == 0) {
                            System.out.println("\nStaff Details are not available, Please enter a valid ID!!");
                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    break;

                case 5:
                    try {
                        al = (ArrayList<Staff>) ois.readObject();

                    } catch (ClassNotFoundException e2) {

                        System.out.println(e2);
                    } catch (Exception e2) {

                        System.out.println(e2);
                    }
                    display(al);
                    break;

                case 6:
                    try {
                        fos = new FileOutputStream(f);
                        oos = new ObjectOutputStream(fos);
                        oos.writeObject(al);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    } finally {
                        try {
                            fis.close();
                            ois.close();
                            fos.close();
                            oos.close();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }

                    }
                    System.out.println("\nYou have chosen EXIT !! Saving Files and closing the tool.");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("\nEnter a correct choice from the List :");
                    break;

            }
        } while (true);
    }

}
