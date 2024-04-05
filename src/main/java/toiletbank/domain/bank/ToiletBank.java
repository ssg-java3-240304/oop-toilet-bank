package toiletbank.domain.bank;

import static toiletbank.constants.Banks.TOILET_BANK;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import toiletbank.domain.customer.Customer;
import toiletbank.domain.customer.Customers;
import toiletbank.domain.transaction.Transactions;
import toiletbank.domain.account.Account;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DecimalFormat;

public class ToiletBank extends Bank {
    private final Map<Bank, Customers> banks;

    public ToiletBank() {
        super(3, TOILET_BANK);
        this.banks = loadBanks();
    }

    public Map<Bank, Customers> getBanks() {
        return banks;
    }

    private Map<Bank, Customers> loadBanks() {
        Map<Bank, Customers> loadBanks = new LinkedHashMap<>();

        List<Bank> banks = new ArrayList<>();
        banks.add(new HanaBank());
        banks.add(new KbBank());
        banks.add(this);

        for (Bank bank : banks) {
            File customerFile = new File("src/main/java/toiletbank/repository/",
                    bank.getName().replace(" ", "") + ".dat");
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(customerFile))) {
                loadBanks.put(bank, (Customers) ois.readObject());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return loadBanks;
    }

    public Map<Bank, List<List<String>>> getAccounts(Customer customer) {
        Map<Bank, List<List<String>>> result = new LinkedHashMap<>();
        DecimalFormat df = new DecimalFormat("#,###");

        for (Map.Entry<Bank, Customers> banksEntry : banks.entrySet()) {
            List<List<String>> accounts = new ArrayList<>();
            Map<Customer, List<Account>> customers = banksEntry.getValue().getCustomers();
            List<Account> customerAccounts = customers.get(customer);
            if (customerAccounts != null) {
                for (Account account : customerAccounts) {
                    accounts.add(
                            List.of(account.getType().getName(), account.getNumber(), df.format(account.getBalance())));
                }
            }
            result.put(banksEntry.getKey(), accounts);
        }

        return result;
    }

    public List<String> getAccount(Customer customer, Integer bankOrder, Integer accountOrder) {
        Map<Bank, List<List<String>>> accounts = getAccounts(customer);
        Bank result = null;
        for (Bank bank : accounts.keySet()) {
            if (bank.getOrder().equals(bankOrder)) {
                result = bank;

            }
        }

        return getAccountDetails(result, customer, banks.get(result).getCustomers().get(customer).get(accountOrder-1));
    }

    public Transactions getTransaction(Customer customer,  Integer bankOrder, Integer accountOrder) {
        Map<Bank, List<List<String>>> accounts = getAccounts(customer);
        Bank result = null;
        for (Bank bank : accounts.keySet()) {
            if (bank.getOrder().equals(bankOrder)) {
                result = bank;
            }
        }

        return getTransaction(result,customer, banks.get(result).getCustomers().get(customer).get(accountOrder-1));
    }

    private List<String> getAccountDetails(Bank bank, Customer customer, Account customerAccount) {
        DecimalFormat df = new DecimalFormat("#,###");
        Account result = null;
        for (Account account : banks.get(bank).getCustomers().get(customer)) {
            if (account.equals(customerAccount)) {
                result = account;
            }
        }

        if (result == null) {
            return null;
        }

        return List.of(result.getBank().getName(), result.getType().getName(), result.getNumber(), String.valueOf(result.getInterestRate()) + " %", df.format(result.getBalance()) + "원");
    }

    private Transactions getTransaction(Bank bank, Customer customer, Account customerAccount) {
        Account result = null;
        for (Account account : banks.get(bank).getCustomers().get(customer)) {
            if (account.equals(customerAccount)) {
                result = account;
            }
        }

        if (result == null) {
            return null;
        }

        return result.getTransactions();
    }
//
//    private Scanner scanner = new Scanner(System.in);
//
//
////    // 사용자에게 계좌번호를 입력받아 원래 계좌 목록에서 일치하면 반환하는 메소드
////    private Account findAccountByNumber(String accountNumber) {
////        for (Map.Entry<Customer, List<Account>> entry : getCustomers().entrySet()) {
////            List<Account> accounts = entry.getValue();
////            for (Account account : accounts) {
////                if (account.getNumber().equals(accountNumber)) {
////                    return account;  // 계좌 객체 반환
////                }
////            }
////        }
////        return null; // 계좌 번호에 해당하는 계좌가 없는 경우
////    }
////
////
////    // 계좌 번호를 이용해 해당 계좌의 소유자 이름을 반환하는 메소드
////    public String findOwnerNameByAccountNumber(String accountNumber) {
////        for (Map.Entry<Customer, List<Account>> entry : getCustomers().entrySet()) {
////            List<Account> accounts = entry.getValue();
////            for (Account account : accounts) {
////                if (account.getNumber().equals(accountNumber)) {
////                    return entry.getKey().getName(); // 계좌의 소유자 이름 반환
////                }
////            }
////        }
////        return "계좌 소유자를 찾을 수 없습니다."; // 계좌 번호에 해당하는 계좌가 없는 경우
////    }
////
//////    // 계좌 번호를 이용해 해당 계좌의 은행명과 일치하는지 확인하는 메소드
//////    public String findBankNameByAccountNumber(String accountNumber) {
//////        // @@ 확인 추가
//////        for (Map.Entry<Customer,List<Account>> entry : getCustomers().entrySet()){
//////            List<Account> accounts = entry.getValue();
//////            for (Account account : accounts) {
//////                if (account.getNumber().equals(accountNumber)) {
//////                    // 계좌번호 일치하면, 해당 계좌의 은행명 반환
//////                    return account.getBank().getName(); // 뱅크의 뱅크네임
//////                }
//////            }
//////        }
//////        return "계좌번호와 은행명이 불일치 합니다."; // 일치하지 않으면
//////    }
//
//    // 사용자에게 계좌번호를 입력받아 일치하는 계좌를 반환하는 메소드
//    private Account findAccountByNumber(String accountNumber) {
//        for (Map.Entry<Bank, Customers> bankEntry : banks.entrySet()) {
//            for (Map.Entry<Customer, List<Account>> customerEntry : bankEntry.getValue().getCustomers().entrySet()) {
//                for (Account account : customerEntry.getValue()) {
//                    if (account.getNumber().equals(accountNumber)) {
//                        return account; // 계좌 객체 반환
//                    }
//                }
//            }
//        }
//        return null; // 계좌 번호에 해당하는 계좌가 없는 경우
//    }
//
//    // 계좌 번호를 이용해 해당 계좌의 소유자 이름을 반환하는 메소드
//    public String findOwnerNameByAccountNumber(String accountNumber) {
//        for (Map.Entry<Bank, Customers> bankEntry : banks.entrySet()) {
//            for (Map.Entry<Customer, List<Account>> customerEntry : bankEntry.getValue().getCustomers().entrySet()) {
//                for (Account account : customerEntry.getValue()) {
//                    if (account.getNumber().equals(accountNumber)) {
//                        return customerEntry.getKey().getName(); // 계좌의 소유자 이름 반환
//                    }
//                }
//            }
//        }
//        return "계좌 소유자를 찾을 수 없습니다."; // 계좌 번호에 해당하는 계좌가 없는 경우
//    }
//
//    // 계좌 번호를 이용해 해당 계좌의 은행명과 일치하는지 확인하는 메소드
//    public String findBankNameByAccountNumber(String accountNumber) {
//        for (Map.Entry<Bank, Customers> bankEntry : banks.entrySet()) {
//            for (Map.Entry<Customer, List<Account>> customerEntry : bankEntry.getValue().getCustomers().entrySet()) {
//                for (Account account : customerEntry.getValue()) {
//                    if (account.getNumber().equals(accountNumber)) {
//                        return bankEntry.getKey().getName(); // 해당 계좌의 은행명 반환
//                    }
//                }
//            }
//        }
//        return "계좌번호와 은행명이 불일치 합니다."; // 일치하는 계좌가 없으면
//    }
//
//
//    // 은행명 입력 메소드
//    public String inputBankName()
//    {
//        System.out.println("은행명을 선택해주세요.");
//        String bankName = scanner.next(); // 받는건 1이지만 리턴값이 string이라서 String으로 받기
//        return bankName;
//    }
//
//    // 계좌 번호입력 메소드
//    public String inputAccountNumber()
//    {
//        String AccountNumber = scanner.next();
//        return AccountNumber;
//    }
//
//    // 이체 금액 입력받기
//    public BigInteger inputAmount() {
//        System.out.println("금액을 입력해주세요. ");
//        BigInteger amount = scanner.nextBigInteger();
//        return amount;
//    }
//
//    //돈빠져나갈 계좌 비밀번호 입력받기
//    public String inputPassword() {
//        System.out.print("비밀번호를 입력하세요: ");
//        return scanner.next(); // 사용자로부터 비밀번호 입력
//    }
//
//    // 사용자 인증을 진행하는 메서드
//    private boolean authenticateAccount(Account account) {
//        String inputPassword = inputPassword(); // 비밀번호 입력받기
//        return account.verifyPassword(inputPassword); // 입력받은 비밀번호와 계좌의 비밀번호 비교
//    }
//
//    //
//
//    //계좌이체
//    public void startBankingProcess() {
//
//        // 송금할 계좌 입력받기
//        // 은행별 계좌 선택 번호 m 입력
//        // 계좌목록 출력 @ (입출금통장만 가져오는거)
////        System.out.print("송금할 계좌 번호를 입력하세요: ");
////        String fromAccountNumber = scanner.next();
//
//
//        System.out.print("출금계좌 : ");
//
//        Account fromAccount = findAccountByNumber(inputAccountNumber()); // 계좌일치 메소드 호출
//
//        // 이체 받을 계좌 입력받기
//
//
//
//
//        // 계좌목록 출력 @@ 안했음 넣을까 말까 ....
//
//        System.out.print("입금 계좌 : ");
//        Account toAccount = findAccountByNumber(inputAccountNumber()); // 계좌일치 메소드 호출
//
//
//
////        //@@ 은행명 입력받고 bankName에 대입받는건데... 변수명 좀 애매한거 같고 이게 맞는가?
////        System.out.println("은행명을 선택해주세요.");
////        String bankName = scanner.next(); // 받는건 1이지만 리턴값이 string이라서 String으로 받기
////        switch (bankName) {
////            case "1":
////                // 송금 메소드 호출
////                findBankNameByAccountNumber(toAccountNumber);
////                bankName = "HANABANK"; //일단 해놨는데 enum으로 바꿔야될거같긴함.
////                break;
////            case "2":
////                findBankNameByAccountNumber(toAccountNumber);
////                bankName = "KbBANK"; //일단 해놨는데 enum으로 바꿔야될거같긴함.
////                break;
////            case "3":
////                findBankNameByAccountNumber(toAccountNumber);
////                bankName = "toiletBank"; //일단 해놨는데 enum으로 바꿔야될거같긴함.
////                break;
////            default:
////                System.out.println("잘못된 입력입니다.");
////                return;
////        }
//
////        //@@ 은행명 입력받고 bankName에 대입받는건데... 변수명 좀 애매한거 같고 이게 맞는가?
////        System.out.println("은행명을 선택해주세요.");
////        String bankName = scanner.next(); // 받는건 1이지만 리턴값이 string이라서 String으로 받기
////
////
////        switch (bankName) {
////            case "1":
////                // 송금 메소드 호출
////                findBankNameByAccountNumber(toAccountNumber);
////                bankName = "하나은행"; //일단 해놨는데 enum으로 바꿔야될거같긴함.
////                break;
////            case "2":
////                findBankNameByAccountNumber(toAccountNumber);
////                bankName = "국민은행"; //일단 해놨는데 enum으로 바꿔야될거같긴함.
////                break;
////            case "3":
////                findBankNameByAccountNumber(toAccountNumber);
////                bankName = "토일렛은행"; //일단 해놨는데 enum으로 바꿔야될거같긴함.
////                break;
////            default:
////                System.out.println("잘못된 입력입니다.");
////                return;
////        }
//
//        //@@ 은행명 입력받고 bankName에 대입받는건데... 변수명 좀 애매한거 같고 이게 맞는가?
//
//        // 계좌이체 최종확인 출력
//        String transferRight = String.format("""
//                %s에게 %d을 보내는게 맞습니까?
//
//                받는 은행 : %s
//                받는 계좌 : %s
//
//                 """,findOwnerNameByAccountNumber(toAccount.getNumber()), inputAmount(),
//                inputBankName(),
//                toAccount.getNumber());
//
//
//        System.out.println(transferRight);
//
//        System.out.println("y 또는 n을 입력해주세요.");
//        char isConfirmed = scanner.next().charAt(0); // 입력받으면 소문자로 변환하는거 해야됨 아직 안함
//
//
//        switch (isConfirmed) {
//            case 'y':
//                // 송금 메소드 호출
//                // 계좌 객체를 찾은 후
//                fromAccount = findAccountByNumber(inputAccountNumber());
//                // 사용자 인증하기
//                if (fromAccount != null && authenticateAccount(fromAccount)) {
//                    // 인증 성공, 이체 프로세스 계속
//                    transfer(fromAccount, toAccount, inputAmount());
//                } else {
//                    System.out.println("비밀번호가 잘못되었습니다.");
//                    // 인증 실패, 프로세스 중단 또는 재시도 로직
//                }
//                break;
//            case 'n':
//                // @@ 메뉴로 돌아가기 인데.. 메뉴가 어딨지.. 추가해야됨
//                System.out.println("이체를 종료합니다.");
//                break;
//            default:
//                System.out.println("잘못된 입력입니다.");
//                // @@ 어디로 가야하나......?
//                break;
//        }
//
//    }
//
//
//    //test ()
//
////    public static void main(String[] args) {
////
////        // Customer 객체 생성
////        Customer customer1 = new Customer("김나경", "990101-2000000");
////        Customer customer2 = new Customer("변성일", "980101-1000000");
////        Customer customer3 = new Customer("심재람", "980201-1000001");
////
////// Account 객체 생성 (예시로 Account의 구체적인 구현체가 필요합니다.)
////        Account account1 = new SavingsAccount(HANA_BANK,"0101"); // 생성자에 필요한 인자를 채워주세요.
////        Account account2 = new SavingsAccount(TOILET_BANK,"1234"); // 생성자에 필요한 인자를 채워주세요.
////        Account account3 = new SavingsAccount(KB_BANK,"7777"); // 다른 고객을 위한 계좌.
////
////// Account를 List에 추가
////        List<Account> accountsCustomer1 = new ArrayList<>();
////        accountsCustomer1.add(account1);
////        accountsCustomer1.add(account2);
////
////        List<Account> accountsCustomer2 = new ArrayList<>();
////        accountsCustomer2.add(account3);
////
////// getCustomers()를 사용하여 customers Map에 접근하고 수정
////
////        ToiletBank bank = new ToiletBank("토일렛은행");
////        Map<Customer, List<Account>> customersMap = bank.getCustomers();
////        customersMap.put(customer1, accountsCustomer1);
////        customersMap.put(customer2, accountsCustomer2);
////
////        bank.startBankingProcess();
//


    }





