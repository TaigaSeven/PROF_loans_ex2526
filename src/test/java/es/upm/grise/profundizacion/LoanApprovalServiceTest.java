package es.upm.grise.profundizacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import es.upm.grise.profundizacion.LoanApprovalService.Decision;

public class LoanApprovalServiceTest {
    
    private final LoanApprovalService service = new LoanApprovalService();

    //DL - Comprueba que devuelve Reject, cuando score < 500
    @Test
    void TODO_caso_camino_basico_1() {
        LoanApprovalService.Applicant applicant = new LoanApprovalService.Applicant(1000,100,true,true);
        LoanApprovalService.Decision decision = service.evaluateLoan(applicant, 1000, 12);
        assertEquals(Decision.REJECTED, decision);
    }

    //DL - Comprueba que devuelve MANUAL_REVIEW, cuando 500 < score < 650, income >= 2500 && !hasDefaults
    @Test
    void TODO_caso_camino_basico_2() {
        LoanApprovalService.Applicant applicant = new LoanApprovalService.Applicant(3000,550,false,true);
        LoanApprovalService.Decision decision = service.evaluateLoan(applicant, 1000, 12);
        assertEquals(Decision.MANUAL_REVIEW, decision);
    }

    //DL - Comprueba que devuelve REJECT, cuando 500 < score < 650, income <2500
    @Test
    void TODO_caso_camino_basico_3() {
        LoanApprovalService.Applicant applicant = new LoanApprovalService.Applicant(2000,600,false,false);
        LoanApprovalService.Decision decision = service.evaluateLoan(applicant, 1000, 12);
        assertEquals(Decision.REJECTED, decision);
    }
    
    //DL - Comprueba que devuelve REJECT, cuando 500 < score < 650, hasDefaults
    @Test
    void TODO_caso_camino_basico_4() {
        LoanApprovalService.Applicant applicant = new LoanApprovalService.Applicant(3000,600,true,false);
        LoanApprovalService.Decision decision = service.evaluateLoan(applicant, 1000, 12);
        assertEquals(Decision.REJECTED, decision);
    }

    //DL - Comprueba que devuelve APPROVED, cuando score > 650, amountRequested <= income * 8
    @Test
    void TODO_caso_camino_basico_5() {
        LoanApprovalService.Applicant applicant = new LoanApprovalService.Applicant(3000,700,true,false);
        LoanApprovalService.Decision decision = service.evaluateLoan(applicant, 10000, 12);
        assertEquals(Decision.APPROVED, decision);
    }

    //DL - Comprueba que devuelve MANUAL_REVIEW, cuando score > 650, amountRequested > income * 8
    @Test
    void TODO_caso_camino_basico_6() {
        LoanApprovalService.Applicant applicant = new LoanApprovalService.Applicant(3000,700,true,true);
        LoanApprovalService.Decision decision = service.evaluateLoan(applicant, 30000, 12);
        assertEquals(Decision.MANUAL_REVIEW, decision);
    }

    //DL - Comprueba que devuelve MANUAL_REVIEW, cuando score > 650, amountRequested > income * 8, pero no es VIP
    @Test
    void TODO_caso_camino_basico_6_2() {
        LoanApprovalService.Applicant applicant = new LoanApprovalService.Applicant(3000,700,true,false);
        LoanApprovalService.Decision decision = service.evaluateLoan(applicant, 30000, 12);
        assertEquals(Decision.MANUAL_REVIEW, decision);
    }

    //DL - Comprueba que devuelve APPROVED, cuando previamente requiere un MANUAL_REVIEW
    @Test
    void TODO_caso_camino_basico_7() {
        LoanApprovalService.Applicant applicant = new LoanApprovalService.Applicant(3000,700,false,true);
        LoanApprovalService.Decision decision = service.evaluateLoan(applicant, 30000, 12);
        assertEquals(Decision.APPROVED, decision);
    }

    //DL - Comprueba que salta mensaje cuando Applicant es null
    @Test
    void TODO_caso_camino_basico_8() {
        assertThrows(NullPointerException.class, () -> service.evaluateLoan(null, 100, 12));
    }
    
    //DL - Comprueba que salta mensaje cuando amountRequested <= 0
    @Test
    void TODO_caso_camino_basico_9() {
        LoanApprovalService.Applicant applicant = new LoanApprovalService.Applicant(1000,100,true,true);
        assertThrows(IllegalArgumentException.class, () -> service.evaluateLoan(applicant, -1, 12));
    }

    //DL - Comprueba que salta mensaje cuando termMonths < 6
    @Test
    void TODO_caso_camino_basico_10() {
        LoanApprovalService.Applicant applicant = new LoanApprovalService.Applicant(1000,100,true,true);
        assertThrows(IllegalArgumentException.class, () -> service.evaluateLoan(applicant, 1000, 5));
    }

    //DL - Comprueba que salta mensaje cuando termMonths > 84
    @Test
    void TODO_caso_camino_basico_11() {
        LoanApprovalService.Applicant applicant = new LoanApprovalService.Applicant(1000,100,true,true);
        assertThrows(IllegalArgumentException.class, () -> service.evaluateLoan(applicant, 1000, 85));
    }

     //DL - Comprueba que salta mensaje cuando applicant.monthlyIncome() <= 0
     @Test
     void TODO_caso_camino_basico_12() {
         LoanApprovalService.Applicant applicant = new LoanApprovalService.Applicant(-1,100,true,true);
         assertThrows(IllegalArgumentException.class, () -> service.evaluateLoan(applicant, 1000, 83));
     }

     //DL - Comprueba que salta mensaje cuando creditScore() < 0
     @Test
     void TODO_caso_camino_basico_13() {
         LoanApprovalService.Applicant applicant = new LoanApprovalService.Applicant(1000,-1,true,true);
         assertThrows(IllegalArgumentException.class, () -> service.evaluateLoan(applicant, 1000, 83));
     }

     //DL - Comprueba que salta mensaje cuando applicant.creditScore() > 850
     @Test
     void TODO_caso_camino_basico_14() {
         LoanApprovalService.Applicant applicant = new LoanApprovalService.Applicant(1000,900,true,true);
         assertThrows(IllegalArgumentException.class, () -> service.evaluateLoan(applicant, 1000, 83));
     }
}
