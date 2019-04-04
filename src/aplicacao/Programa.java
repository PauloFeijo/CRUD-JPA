package aplicacao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dominio.Pessoa;

public class Programa {

	public static void main(String[] args) {
		//criarPessoas();
		//buscarPessoa();
		//removerPessoa();
		//alterarPessoa();
		listarPessoas();
	}
	
	public static void criarPessoas() {
		Pessoa p1 = new Pessoa(null,"Jose", "jose@email.com");
		Pessoa p2 = new Pessoa(null,"Maria", "maria@email.com");
		Pessoa p3 = new Pessoa(null,"Pedro", "pedro@email.com");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		System.out.println("Pessoas Criadas!");		
	}
	
	public static void buscarPessoa() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();
		
		Pessoa p = em.find(Pessoa.class, 50);
		System.out.println(p);
		
		em.close();
		emf.close();
	}
	
	public static void removerPessoa() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();
		
		Pessoa p = em.find(Pessoa.class, 2);
		
		em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		System.out.println("Pessoa removida!");
	}
	
	public static void alterarPessoa() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();
		
		Pessoa p = em.find(Pessoa.class, 1);
		
		p.setNome("Paulo alterado");
		
		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		System.out.println("Pessoa alterada!");	
	}
	
	public static void listarPessoas() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createQuery("SELECT e FROM Pessoa e");
		
		List<Pessoa> pessoas = q.getResultList();
		
		for (Pessoa p : pessoas) {
			System.out.println(p.getNome());
		}
		
		em.close();
		emf.close();
	}
}
