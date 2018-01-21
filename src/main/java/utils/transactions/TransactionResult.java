package utils.transactions;

/**
 * Result of a transaction.
 */
public enum TransactionResult {
    COMMIT,
    ROLLBACK,
    ERROR_WHILE_ROLLBACK
}
