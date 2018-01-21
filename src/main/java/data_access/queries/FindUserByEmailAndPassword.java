package data_access.queries;

import models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jasypt.util.password.StrongPasswordEncryptor;
import orm.repository.IRepository;
import orm.repository.impl.sql.queries.GetEntityQuery;
import orm.repository.impl.sql.queries.SqlQueryContext;

import java.util.List;

/**
 * Find user with given credentials.
 */
public class FindUserByEmailAndPassword extends GetEntityQuery<User> {
    private static final Logger logger = LogManager.getLogger(FindUserByEmailAndPassword.class);
    private final IRepository<User> repository = context.getRepositoryManager().get(User.class);
    private final StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();

    private String plainPassword;
    private String email;

    public FindUserByEmailAndPassword withEmail(String email) {
        logger.info("started remembering email");
        this.email = email;
        logger.info("remembered email");
        return this;
    }

    public FindUserByEmailAndPassword withPassword(String password) {
        logger.info("started remembering password");
        this.plainPassword = password;
        logger.info("remembered password");
        return this;
    }

    public FindUserByEmailAndPassword(SqlQueryContext<User> context) {
        super(
                context,
                "SELECT * FROM users WHERE email=? and password=?;"
        );
        logger.info("constructed");
    }

    @Override
    public User get() {
        List<User> users = repository.getAll();

        return users.stream()
                .filter(
                        u -> u.getEmail().getValue().equalsIgnoreCase(email) && encryptor.checkPassword(
                                plainPassword, u.getPassword().getValue()
                        )

                )
                .findFirst()
                .orElse(null);

    }
}
