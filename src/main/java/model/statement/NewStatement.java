package model.statement;

import exceptions.MyException;
import model.expression.IExpression;
import model.programState.ProgramState;
import model.type.RefType;
import model.type.Type;
import model.utils.MyIDictionary;
import model.utils.MyIHeap;
import model.utils.MyIStack;
import model.value.RefValue;
import model.value.Value;

public class NewStatement implements IStatement{
    private final String varName;
    private final IExpression expression;

    public NewStatement(String varName, IExpression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<MyIDictionary<String, Value>> symbolTables = state.getSymTable();
        MyIDictionary<String, Value> symTable = symbolTables.peek();
        MyIHeap heap = state.getHeap();
        if (symTable.isDefined(varName)) {
            Value varValue = symTable.lookUp(varName);
            if ((varValue.getType() instanceof RefType)) {
                Value evaluated = expression.eval(symTable, heap);
                Type locationType = ((RefValue) varValue).getLocationType();
                if (locationType.equals(evaluated.getType())) {
                    int newPosition = heap.add(evaluated);
                    symTable.put(varName, new RefValue(newPosition, locationType));
                    state.setSymTable(symbolTables);
                    state.setHeap(heap);
                } else
                    throw new MyException(String.format("%s not of %s", varName, evaluated.getType()));
            } else {
                throw new MyException(String.format("%s in not of RefType", varName));
            }
        } else {
            throw new MyException(String.format("%s not in symTable", varName));
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookUp(varName);
        Type typeExpr = expression.typeCheck(typeEnv);
        if (typeVar.equals(new RefType(typeExpr)))
            return typeEnv;
        else
            throw new MyException("NEW statement: right hand side and left hand side have different types.");
    }

    @Override
    public IStatement deepCopy() {
        return new NewStatement(varName, expression.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("New(%s, %s)", varName, expression);
    }
}