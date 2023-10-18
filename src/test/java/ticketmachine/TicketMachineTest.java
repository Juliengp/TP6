package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
                // Les montants ont été correctement additionnés  
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");              
	}

	@Test

	void nImprimerPasSimontantInsuffisant(){
		machine.insertMoney( PRICE -1);
		assertFalse(machine.printTicket(),"Le ticket ne doit pas être imprimé");
	}

	@Test

	void imprimeSiMontantSuffisant(){
		machine.insertMoney(PRICE +1);
		assertTrue(machine.printTicket(),"Le ticket aurait dû être imprimé");
	}

	@Test

	void decrementationQuandTicketImprime(){
		machine.insertMoney(PRICE + 10);
		machine.printTicket();
		assertEquals(10,machine.getBalance(), "La balance aurait dû être décrémentée");
	}

	@Test

	void montantMisAJourQuandTicketImprime(){
		machine.insertMoney(PRICE);
		machine.printTicket();
		assertEquals(PRICE,machine.getTotal(),"le montant n'a pas été mis à jour");
	}

	@Test

	void montantPasMisAJourQuandTicketNonImprime(){
		machine.insertMoney(PRICE);
		assertEquals(0,machine.getTotal(),"le montant n'aurais pas dû être mis à jour");
}

	@Test

	void rendCorrectementLaMonnaie(){
		machine.insertMoney(PRICE+10);
		machine.printTicket();
		assertEquals(10,machine.refund(),"La monnaie n'a pas été rendue");
	}

	@Test

	void remetLaBalanceAZero(){
		machine.insertMoney(PRICE+10);
		machine.printTicket();
		machine.refund();
		assertEquals(0,machine.getBalance(),"La balance n'a pas été remise à zéro");
	}

	@Test

	void onNePeutPasInsererDargentNegatif(){
		try{
			machine = new TicketMachine(-50);
			fail("Argument mus not be negative");}
			catch(IllegalArgumentException e){};
		}
	

	@Test

	void pasTicketNeg(){
		try{
			machine = new TicketMachine(-30);
			fail("Argument mus not be negative");}
			catch(IllegalArgumentException e){};
		}
	
	}


