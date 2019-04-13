package Service;

import Domain.Entity;
import Repository.IRepository;

public class DeleteOperation<T extends Entity> extends UndoRedoOperation {
    private T deletedEntity;

    DeleteOperation(IRepository<T> repository, T deletedEntity) {
        super(repository);
        this.deletedEntity = deletedEntity;
    }

    @Override
    public void doUndo() {
        repository.insert(deletedEntity);
    }

    @Override
    public void doRedo() {
        repository.remove(deletedEntity.getId());
    }
}

