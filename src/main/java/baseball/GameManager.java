package baseball;

import VO.InputNumbers;
import VO.ReplyNumber;
import VO.ResultNumbers;

public class GameManager {
    String answer;
    NumberGenerator numberGenerator = new NumberGenerator();
    MessagePrint messagePrint = new MessagePrint();
    UserInput userInput = new UserInput();
    GameResult gameResult = new GameResult();

    public void run() {
        answer = numberGenerator.numberGenerate();
        int strikeCnt = 0;
        int ballCnt = 0;
        ResultNumbers result;
        do {
            messagePrint.requestInput();
            InputNumbers input = new InputNumbers(userInput.answerInput());
            strikeCnt = gameResult.getStrikeCnt(input, answer);
            ballCnt = gameResult.getBallCnt(input, answer);
            result = new ResultNumbers(strikeCnt, ballCnt);
            messagePrint.getResultMessage(result);

        } while (requestProgress(result));
    }

    public boolean requestProgress(ResultNumbers result) {
        if (result.isAnswer()) {
            messagePrint.restartRequest();
            ReplyNumber reply = new ReplyNumber(userInput.answerInput());
            if (reply.isRestart()) {
                answer = resetAnswer();
                return true;
            } else if (reply.isExit()) {
                return false;
            }
        }
        return true;
    }

    public String resetAnswer() {
        return numberGenerator.numberGenerate();
    }
}
