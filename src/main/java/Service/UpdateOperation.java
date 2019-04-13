package Service;

import Domain.Entity;
import Repository.IRepository;

public class UpdateOperation<T extends Entity> extends UndoRedoOperation {
    private T updatedEntity;
    private T actualEntity;

    UpdateOperation(IRepository<T> repository, T updatedEntity, T actualEntity) {
        super(repository);
        this.updatedEntity = updatedEntity;
        this.actualEntity = actualEntity;
    }

    @Override
    public void doUndo() {

        repository.update(actualEntity);
    }

    @Override
    public void doRedo() {
        repository.update(updatedEntity);
    }
}
