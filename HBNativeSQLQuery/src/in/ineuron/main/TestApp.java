package in.ineuron.main;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import in.ineuron.model.InsurancePolicy;
import in.ineuron.util.HibernateUtil;

public class TestApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session = null;
		session=HibernateUtil.getSession();
		Transaction transaction = null;
		int count1 = 0,count2 = 0;
		boolean flag1 = false;
		boolean flag2 = false;
		
		
		try {
			 
			 
			
			NativeQuery<Object[]> query1 =session.getNamedNativeQuery("GET_ALL_POLICY_TYPE");
			
			query1.setParameter("type", "yearly");
			List<Object[]> list = query1.getResultList();
			System.out.println("======FETCH SPECIFIC COLUMNS============");
			System.out.println("POLICYID\tCOMPANY\tPOLICYNAME");
			list.forEach(row->{
				for(Object obj:row) {
					System.out.print(obj+"\t\t");
				}
				System.out.println();
			});
			
			NativeQuery<InsurancePolicy> query2 =session.getNamedNativeQuery("GET_ALL_POLICIES");
			List<InsurancePolicy> policy = query2.getResultList();
			
			System.out.println();
			System.out.println("==========FETCH ALL COLUMNS=================");
			policy.forEach(items->System.out.println(items));
			
			//Deletion using id
			System.out.println();
			  
			
			if (!session.getTransaction().isActive()) {
				transaction = session.beginTransaction();
				NativeQuery<InsurancePolicy> query3 = session.getNamedNativeQuery("DELETE_POLICY_BY_ID");
				query3.setParameter("id", 4l);
				count1 = query3.executeUpdate();
				flag1 = true;
			}
			
			System.out.println();
			if (session.getTransaction().isActive()) {
				NativeQuery query4 = session.createSQLQuery("insert into insurancepolicy(company,policyname,policytype,tenure) values(?,?,?,?)");
				query4.setParameter(1, "TATA");
				query4.setParameter(2, "Life");
				query4.setParameter(3, "yearly");
				query4.setParameter(4,25);
				count2 = query4.executeUpdate();
				flag1 = true;
			}
			
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
		    if(flag1) {
		    	transaction.commit();
		    	System.out.println("No of records deleted are :: "+count1);
		    }
		    	
		    else {
		    	transaction.rollback();
		    	System.out.println("Record deletion failed");
		    }
		    
//		    if(flag2) {
//		    	transaction.commit();
//		    	System.out.println("No of records inserted are :: "+count2);
//		    }
//		    	
//		    else {
//		    	transaction.rollback();
//		    	System.out.println("Record insertion failed");
//		    }
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
