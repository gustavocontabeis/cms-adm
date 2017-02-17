package br.com.imob.geradorcodigo;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.Test;

import br.com.tche.geradorcodigo.GeradorCodigo;
import br.com.tche.geradorcodigo.classes.Aplicacao;
import br.com.tche.geradorcodigo.classes.GeradorJSF;
import br.com.tche.geradorcodigo.classes.GeradorManagedBean;
import br.com.tche.geradorcodigo.util.ReflectionUtil2;;

/**
 * O gerador de código deverá:
 * 
 * Gerar a Classe/Entidade
 * Registro no hibernate.cfg.xml
 * insert no import.sql
 * DAO com crud
 * teste unitírio da DAO
 * ManagedBean
 * página JSF.
 * Formulário CRUD com <navigation-rule> do faces-config.xml
 * ítem do menu.
 * Todos ítens gerar labels no Resource Bunble.
 * 
 */
//@Ignore
public class GeradorCodigoTest{
	
	Object objeto;
	Aplicacao aplicacao;
	
	@Before
	public void init(){
		aplicacao = new Aplicacao();
		aplicacao.setPackageApp("br.com.maxig");
		aplicacao.setWebAppPath("/home/caixa/coder/projects/cms-adm/src/main/webapp");//caixa
		objeto = new br.com.coder.cms.model.entity.Galeria();
	}
	
	@Test //@Ignore
	public void testGerarPojo() {
		System.out.println(GeradorCodigo.gerarPojo(objeto.getClass()));
	}
	
	//@Test //@Ignore
	public void testPopularPojo() throws Exception {
		Object objeto = GeradorCodigo.gerarObjeto(this.objeto.getClass(), true);
		System.out.println(GeradorCodigo.gerarCodigoNovoObjeto(objeto));
	}

	//@Test //@Ignore
	public void testGerarSqlInsert() throws Exception {
		System.out.println(GeradorCodigo.gerarSqlInsert(objeto.getClass()));
	}
	
	@Test //@Ignore
	public void testGerarDAO() {
		System.out.println(GeradorCodigo.gerarDAO(objeto.getClass()));
	}
	
	//@Test //@Ignore
	public void testGerarJUnit() throws Exception {
		System.out.println(GeradorCodigo.gerarTesteUnitario(objeto.getClass()));
	}
	
	@Test //@Ignore
	public void testGerarManagedBean() throws Exception {
		//System.out.println(GeradorCodigo.gerarManagedBean(classe));
		GeradorManagedBean gmb = new GeradorManagedBean(aplicacao, objeto);
		System.out.println(gmb.gerarCodigo());
	}
	
	//@Test //@Ignore
	public void testPagina() throws Exception {
		int showInternalConfirmDialog = JOptionPane.showConfirmDialog(null, "Tem Certeza que deseja gerar o arquivo?");
		if(showInternalConfirmDialog == 0){
			System.out.println(GeradorCodigo.gerarPagina(aplicacao, objeto.getClass()));
		}
	}

	@Test //@Ignore 
	public void testGerarFormCrudJSF() throws Exception {
		GeradorJSF gerador = new GeradorJSF(objeto);
		System.out.println(gerador.gerarCodigo());
		//System.out.println(GeradorCodigo.formCrudJSF(objeto.getClass()));
	}
	
	//@Test @Ignore
	public void testNavigationRule() throws Exception {
		System.out.println(GeradorCodigo.navigationRule(objeto.getClass()));
	}
	
	//@Test @Ignore
	public void test() throws Exception {
		
		Field[] fields = ReflectionUtil2.getAllFIeldsNotIndexed(null);
		for (Field field : fields) {
			System.out.println(field.getName());
		}
		System.out.println("---------------------------------");
		PropertyDescriptor[] propertyDescriptors = ReflectionUtil2.getPropertyDescriptors(null);
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			System.out.println(propertyDescriptor.getName());
		}
		System.out.println(fields.length +" - " + propertyDescriptors.length);
	}

	
	
}
