package bank;



public class CustomerService {

    private Bank bank;

    public CustomerService(Bank bank) {

        if (bank == null) {
            throw new IllegalArgumentException(
                    "Bank cannot be null."
            );
        }

        this.bank = bank;
    }

    // =====================================================
    // ADD CUSTOMER
    // =====================================================

    public void addCustomer(Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException(
                    "Customer cannot be null."
            );
        }

        if (searchCustomerById(
                customer.getCustomerId()) != null) {

            throw new IllegalArgumentException(
                    "Customer ID already exists."
            );
        }

        if (searchCustomerByNationalId(
                customer.getNationalId()) != null) {

            throw new IllegalArgumentException(
                    "National ID already exists."
            );
        }

        if (searchCustomerByEmail(
                customer.getEmail()) != null) {

            throw new IllegalArgumentException(
                    "Email already exists."
            );
        }

        bank.addCustomer(customer);

        System.out.println(
                "Customer " +
                        customer.getCustomerId() +
                        " has been added successfully."
        );
    }

    // =====================================================
    // SEARCH BY CUSTOMER ID
    // =====================================================

    public Customer searchCustomerById(int id) {

        for (Customer customer :
                bank.getCustomers()) {

            if (customer.getCustomerId() == id) {
                return customer;
            }
        }

        return null;
    }

    // =====================================================
    // SEARCH BY NATIONAL ID
    // =====================================================

    public Customer searchCustomerByNationalId(
            String nationalId) {

        if (nationalId == null ||
                nationalId.trim().isEmpty()) {

            return null;
        }

        nationalId = nationalId.trim();

        for (Customer customer :
                bank.getCustomers()) {

            if (customer.getNationalId()
                    .equals(nationalId)) {

                return customer;
            }
        }

        return null;
    }

    // =====================================================
    // SEARCH BY EMAIL
    // =====================================================

    public Customer searchCustomerByEmail(
            String email) {

        if (email == null ||
                email.trim().isEmpty()) {

            return null;
        }

        email = email.trim();

        for (Customer customer :
                bank.getCustomers()) {

            if (customer.getEmail()
                    .equalsIgnoreCase(email)) {

                return customer;
            }
        }

        return null;
    }

    // =====================================================
    // LOGIN CUSTOMER
    // =====================================================

    public Customer login(
            String username,
            String password) {

        if (username == null ||
                password == null) {

            return null;
        }

        for (Customer customer :
                bank.getCustomers()) {

            if (customer.getUsername()
                    .equals(username)
                    &&
                    customer.getPassword()
                            .equals(password)) {

                return customer;
            }
        }

        return null;
    }

    // =====================================================
    // FIND CUSTOMER
    // =====================================================

    public Customer findCustomerById(int id) {

        Customer customer =
                searchCustomerById(id);

        if (customer == null) {

            throw new IllegalArgumentException(
                    "Customer not found."
            );
        }

        return customer;
    }

    // =====================================================
    // REMOVE CUSTOMER
    // =====================================================

    public void removeCustomer(int id) {

        Customer customer =
                findCustomerById(id);

        bank.getCustomers().remove(customer);

        System.out.println(
                "Customer " +
                        id +
                        " has been removed successfully."
        );
    }

    // =====================================================
    // UPDATE CUSTOMER
    // =====================================================

    public void updateCustomer(
            int customerId,
            String fullName,
            String email,
            String nationalId,
            String address) {

        Customer customer =
                findCustomerById(customerId);

        Customer emailCustomer =
                searchCustomerByEmail(email);

        if (emailCustomer != null &&
                emailCustomer != customer) {

            throw new IllegalArgumentException(
                    "Email already belongs to another customer."
            );
        }

        Customer nationalIdCustomer =
                searchCustomerByNationalId(nationalId);

        if (nationalIdCustomer != null &&
                nationalIdCustomer != customer) {

            throw new IllegalArgumentException(
                    "National ID already belongs to another customer."
            );
        }

        customer.setFullName(fullName);
        customer.setEmail(email);
        customer.setNationalId(nationalId);
        customer.setAddress(address);

        System.out.println(
                "Customer " +
                        customerId +
                        " has been updated successfully."
        );
    }

    // =====================================================
    // GET ALL CUSTOMERS
    // =====================================================

    public void getAllCustomers() {

        if (bank.getCustomers().isEmpty()) {

            System.out.println(
                    "No customers found."
            );

            return;
        }

        for (Customer customer :
                bank.getCustomers()) {

            System.out.println(customer);
        }
    }
}