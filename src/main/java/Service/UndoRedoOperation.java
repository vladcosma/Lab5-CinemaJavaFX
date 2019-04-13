package Service;

import Domain.Entity;
import Repository.IRepository;

public abstract class UndoRedoOperation<T extends Entity> {
    protected IRepository<T> repository;

    UndoRedoOperation(IRepository<T> repository) {
        this.repository = repository;
    }

    public abstract void doUndo();
    public abstract void doRedo();
}
