package DatabaseProject;

import java.util.Scanner;
import java.sql.SQLException;

import DatabaseProject.Exceptions.InvalidScholarshipStatusException;
import DatabaseProject.Exceptions.UserNotLoggedInException;
import DatabaseProject.features.scholarshipverification.impl.*;
import DatabaseProject.features.scholarshipverification.usecases.*;
import DatabaseProject.features.usermanagement.impl.LoginImpl;
import DatabaseProject.features.usermanagement.impl.LogoutImpl;
import DatabaseProject.features.usermanagement.usecases.Login;
import DatabaseProject.features.usermanagement.usecases.Logout;
import DatabaseProject.models.Date;
import DatabaseProject.models.ScholarshipStatus;


public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        ScholarshipLogRecord logRecorder = new ScholarshipLogRecordImpl();

        while(true) {
            System.out.print("Enter your command:");
            String command = scanner.next();

            if(command.equals("signup")) {
                System.out.print("username:");
                String username = scanner.next();
                System.out.print("password:");
                String password = scanner.next();
                System.out.print("first name:");
                String firstName = scanner.next();
                System.out.print("last name:");
                String lastName = scanner.next();
                System.out.print("personalID:");
                String personalID = scanner.next();
                System.out.print("role:");
                String role = scanner.next();
                System.out.print("degree:");
                String degree = scanner.next();
                System.out.print("student gpa:");
                double gpa = scanner.nextDouble();

                try {
                    FillRegistrationFormByStudent fillRegistrationFormByStudent = new FillRegistrationFormByStudentImpl();
                    fillRegistrationFormByStudent.submitPersonalInfo(
                            username,
                            password,
                            firstName,
                            lastName,
                            personalID,
                            role,
                            degree,
                            gpa
                    );
                } catch (FillRegistrationFormByStudent.InvalidUserRoleException e) {
                    e.printStackTrace();
                } catch (FillRegistrationFormByStudent.TooLimitPasswordLengthException e) {
                    e.printStackTrace();
                } catch (FillRegistrationFormByStudent.InvalidGPAException e) {
                    e.printStackTrace();
                } catch (FillRegistrationFormByStudent.UserAlreadyExistsException e) {
                    e.printStackTrace();
                } catch (FillRegistrationFormByStudent.InvalidUniversityDegreeException e) {
                    e.printStackTrace();
                }
            }

            else if(command.equals("login")) {
                System.out.print("username:");
                String userName = scanner.next();
                System.out.print("password:");
                String password = scanner.next();

                try {
                    Login loginByUser = new LoginImpl();
                    loginByUser.login(userName, password);
                } catch (Login.UserNotFoundException e) {
                    System.err.println(e.getMessage());
                }
            }

            else if (command.equals("logout")) {
                System.out.print("username:");
                String userName = scanner.next();

                try {
                    Logout logoutByUser = new LogoutImpl();
                    logoutByUser.logout(userName);
                } catch (Logout.UserNotFoundException e) {
                    System.err.println(e.getMessage());
                }
            }

            else if (command.equals("scholarship")) {
                System.out.print("username:");
                String userName = scanner.next();
                System.out.print("destination university:");
                String destinationUniversity = scanner.next();
                System.out.print("field:");
                String field = scanner.next();
                System.out.print("start date:");
                String startDate = scanner.next();

                try {
                    ScholarshipRequestByStudent scholarshipRequestByStudent = new ScholarshipRequestByStudentImpl();
                    scholarshipRequestByStudent.insertRequestIntoDatabase(userName, destinationUniversity, field, startDate);
                } catch (ScholarshipRequestByStudent.UserNotFoundException e) {
                    System.err.println(e.getMessage());
                } catch (UserNotLoggedInException e) {
                    System.err.println(e.getMessage());
                }
            }

            else if (command.equals("accept_by_supervisor")) {
                System.out.print("supervisor username:");
                String supervisorUsername = scanner.next();
                System.out.print("scholarshipID:");
                int scholarshipID = scanner.nextInt();

                try {
                    ScholarshipVerificationBySupervisor scholarshipVerificationBySupervisor = new ScholarshipVerificationBySupervisorImpl();
                    scholarshipVerificationBySupervisor.accept(scholarshipID, supervisorUsername);
                    logRecorder.recordScholarshipLog(ScholarshipStatus.AcceptedBySupervisor.toString(), Date.getDate(), scholarshipID);
                } catch (ScholarshipVerificationBySupervisor.ScholarshipIDNotFoundException e) {
                    System.err.println(e.getMessage());
                } catch (ScholarshipLogRecord.ScholarshipIDNotFoundException e) {
                    System.err.println(e.getMessage());
                } catch (InvalidScholarshipStatusException e) {
                    System.err.println(e.getMessage());
                } catch (UserNotLoggedInException e) {
                    System.err.println(e.getMessage());
                }
            }

            else if (command.equals("reject_by_supervisor")) {
                System.out.print("scholarshipID:");
                int scholarshipID = scanner.nextInt();

                try {
                    ScholarshipRejectionBySupervisor s = new ScholarshipRejectionBySupervisorImpl();
                    s.reject(scholarshipID);
                    logRecorder.recordScholarshipLog(ScholarshipStatus.AcceptedBySupervisor.toString(), Date.getDate(), scholarshipID);
                } catch(ScholarshipRejectionBySupervisor.ScholarshipIDNotFoundException e) {
                    System.err.println("scholarship id not found!");
                } catch (ScholarshipLogRecord.ScholarshipIDNotFoundException e) {
                    System.err.println(e.getMessage());
                } catch (InvalidScholarshipStatusException e) {
                    System.err.println(e.getMessage());
                } catch (UserNotLoggedInException e) {
                    System.err.println(e.getMessage());
                }
            }

            else if (command.equals("accept_by_manager")) {
                System.out.print("scholarshipID:");
                int scholarshipID = scanner.nextInt();

                try {
                    ScholarshipVerificationByManager s = new ScholarshipVerificationByManagerImpl();
                    s.accept(scholarshipID);
                    logRecorder.recordScholarshipLog(ScholarshipStatus.AcceptedBySupervisor.toString(), Date.getDate(), scholarshipID);
                } catch(ScholarshipVerificationByManager.ScholarshipIDNotFoundException e) {
                    System.err.println("scholarship id not found!");
                } catch (ScholarshipLogRecord.ScholarshipIDNotFoundException e) {
                    System.err.println(e.getMessage());
                } catch (InvalidScholarshipStatusException e) {
                    System.err.println(e.getMessage());
                } catch (UserNotLoggedInException e) {
                    System.err.println(e.getMessage());
                }
            }

            else if (command.equals("reject_by_manager")) {
                System.out.print("scholarshipID:");
                int scholarshipID = scanner.nextInt();

                try {
                    ScholarshipRejectionByManager s = new ScholarshipRejectionByManagerImpl();
                    s.reject(scholarshipID);
                    logRecorder.recordScholarshipLog(ScholarshipStatus.RejectedByManager.toString(), Date.getDate(), scholarshipID);
                } catch (ScholarshipRejectionByManager.ScholarshipIDNotFoundException e) {
                    System.err.println("scholarship id not found!");
                } catch (ScholarshipLogRecord.ScholarshipIDNotFoundException e) {
                    System.err.println(e.getMessage());
                } catch (InvalidScholarshipStatusException e) {
                    System.err.println(e.getMessage());
                } catch (UserNotLoggedInException e) {
                    System.err.println(e.getMessage());
                }
            }

            else if (command.equals("find_scholarship_by_supervisor")) {
                FindScholarshipBySupervisor f = new FindScholarshipBySupervisorImpl();
                f.find();
            }

            else if (command.equals("find_scholarship_by_manager")) {
                FindScholarshipByManager f = new FindScholarshipByManagerImpl();
                f.find();
            }

            else if (command.equals("find_scholarship_by_university")) {
                FindScholarshipByUniversity f = new FindScholarshipByUniversityImpl();
                f.find();
            }

            else if (command.equals("increase date")) {
                Date.nextDay();
            }

            else if (command.equals("exit")) {
                break;
            }
        }
    }
}
