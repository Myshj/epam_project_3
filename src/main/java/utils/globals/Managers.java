package utils.globals;

import models.*;
import utils.factories.repository.SqlRepositoryFactory;
import utils.managers.ConnectionManager;
import utils.managers.MetaInfoManager;
import utils.managers.RepositoryManager;

public class Managers implements IManagers {
    private final IResourceManagers resources = new ResourceManagers();

    private final ConnectionManager connection = new ConnectionManager(resources.getApplication());

    private final RepositoryManager repository = new RepositoryManager(new SqlRepositoryFactory(connection));

    private final MetaInfoManager metaInfo = new MetaInfoManager(
            Country.class, City.class, Street.class, Building.class,
            Showroom.class, Exposition.class, Ticket.class, TicketType.class,
            User.class, UserRole.class, Order.class, OrderState.class
    );

    @Override
    public ConnectionManager getConnection() {
        return connection;
    }

    @Override
    public RepositoryManager getRepository() {
        return repository;
    }

    @Override
    public IResourceManagers getResources() {
        return resources;
    }

    @Override
    public MetaInfoManager getMetaInfo() {
        return metaInfo;
    }
}
