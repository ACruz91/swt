package matriz;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

class Interfaz {
	Display display;
	Shell shell;

	public void menu() {
		// Par�metros
		Menu menuBar, menuArchivo, menuVisualizacion;
		MenuItem menuArchivoCabecera, menuVisualizacionCabecera;
		MenuItem menuArchivoNuevo, menuArchivoGuardar, menuArchivoSalir, menuArchivoAbrir, menuVisualizacionEstadisticas;
		ArrayList<int[][]> arrayListMatriz = new ArrayList<int[][]>();
		ArrayList<Integer> arrayListFilas = new ArrayList<Integer>();
		ArrayList<Integer> arrayListColumnas = new ArrayList<Integer>();
		Matriz obj = new Matriz();

		// Creaci�n del display y shell
		Display display = new Display();
		Shell shell = new Shell(display);

		// Par�metros Ventana
		shell.setText("Ejercicio SWT");
		shell.setMaximized(true);

		// Creaci�n del CTabFolder para las pesta�as
		CTabFolder folder = new CTabFolder(shell, SWT.BORDER);
		folder.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				// Esta parte del c�digo sirve para ayudar a la ventana de las
				// estad�sticas que datos debe de retonar
				// porque en el caso de que se clickea en los items del
				// ctabfolder no se muestras para poder verse debe de
				// clickearse en la tabla

			}
		});
		// FillLayout
		FillLayout fillLayout = new FillLayout(SWT.HORIZONTAL);
		fillLayout.marginWidth = 5;
		fillLayout.marginHeight = 5;
		fillLayout.spacing = 50;
		shell.setLayout(fillLayout);

		// Creaci�n de una Tabla que ser� el "Arbol" de las Matrices
		Table tableMatrices = new Table(shell, SWT.BORDER);
		tableMatrices.setHeaderVisible(true);
		tableMatrices.setLinesVisible(true);
		TableColumn column1 = new TableColumn(tableMatrices, SWT.NONE);
		column1.setText("�ndice de Matrices");
		column1.setWidth(700);
		tableMatrices.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				int indice = 0;
				int indiceFolder = 0;

				// Creaci�n de la Tabla para que se muestre
				Table table = new Table(folder, SWT.BORDER | SWT.MULTI);
				indice = tableMatrices.getSelectionIndex();
				System.out.println(indice);

				// Obtenemos los datos para retornar nuestra Matriz de las
				// arrays.
				obj.setMatriz(arrayListMatriz.get(indice),
						arrayListFilas.get(indice),
						arrayListColumnas.get(indice));

				// Creaci�n de Columnas
				for (int j = 0; j < arrayListColumnas.get(indice); j++) {
					TableColumn column = new TableColumn(table, SWT.BORDER
							| SWT.MULTI | SWT.V_SCROLL);
					column.setText((Integer.toString(j)));
					column.setWidth(30);
				}

				// Creaci�n de la Matriz
				for (int i = 0; i < arrayListFilas.get(indice); i++) {
					TableItem item = new TableItem(table, SWT.NONE);
					for (int j = 0; j < arrayListColumnas.get(indice); j++) {
						item.setText(j, Integer.toString(obj.getMatriz(i, j)));
					}
				}

				// Creaci�n del Item para el CTabItem, contiene un
				// �ndice que indica el n�mero de Matriz
				CTabItem item = new CTabItem(folder, SWT.CLOSE);
				indiceFolder = indice + 1;
				item.setText("Matriz " + indiceFolder);
				item.setControl(table);

				// Asignaci�n
				obj.setFilas(arrayListFilas.get(indice));
				obj.setColumnas(arrayListColumnas.get(indice));

			}
		});

		// Creaci�n del Men�
		menuBar = new Menu(shell, SWT.BAR);

		// Creaci�n de la Cabecera del menuBar, Archivo
		menuArchivoCabecera = new MenuItem(menuBar, SWT.CASCADE);
		menuArchivoCabecera.setText("&Archivo");

		// Creaci�n de la Cabecera del menuBar, Archivo
		menuVisualizacionCabecera = new MenuItem(menuBar, SWT.CASCADE);
		menuVisualizacionCabecera.setText("&Visualizaci�n");

		// Creaci�n del subMen� Archivo es una cabecera del men� menuBar
		menuArchivo = new Menu(shell, SWT.DROP_DOWN);
		menuArchivoCabecera.setMenu(menuArchivo);

		// Creaci�n de un Item del Submen� Archivo, Nuevo
		menuArchivoNuevo = new MenuItem(menuArchivo, SWT.PUSH);
		menuArchivoNuevo.setText("&Nuevo\t CTRL+N");
		menuArchivoNuevo.setAccelerator(SWT.CTRL + 'N');

		// Creaci�n de un Item del Submen� Archivo, Guardar
		menuArchivoAbrir = new MenuItem(menuArchivo, SWT.PUSH);
		menuArchivoAbrir.setText("&Abrir\t CTRL+O");
		menuArchivoAbrir.setAccelerator(SWT.CTRL + 'O');

		// Creaci�n de un Item del Submen� Archivo, Guardar
		menuArchivoGuardar = new MenuItem(menuArchivo, SWT.PUSH);
		menuArchivoGuardar.setText("&Guardar\t CTRL+S");
		menuArchivoGuardar.setAccelerator(SWT.CTRL + 'S');

		// Creaci�n de un Item del Submen� Archivo, Salir
		menuArchivoSalir = new MenuItem(menuArchivo, SWT.PUSH);
		menuArchivoSalir.setText("&Salir\t CTRL+X");
		menuArchivoSalir.setAccelerator(SWT.CTRL + 'X');

		// Creaci�n del subMen� Visualizaci�n es una cabecera del men� menuBar
		menuVisualizacion = new Menu(shell, SWT.DROP_DOWN);
		menuVisualizacionCabecera.setMenu(menuVisualizacion);

		// Creaci�n de un Item del Submen� Visualizaci�n, Estad�sticas
		menuVisualizacionEstadisticas = new MenuItem(menuVisualizacion,
				SWT.PUSH);
		menuVisualizacionEstadisticas.setText("&Estad�sticas\t CTRL+N");
		menuVisualizacionEstadisticas.setAccelerator(SWT.CTRL + 'E');

		// Funciones

		// Funcion nuevo, Crea una nueva Matriz
		class nuevo implements SelectionListener {
			public void widgetSelected(SelectionEvent event) {
				// Creaci�n de una Shell hija llamada "nuevo" para el nuevo
				// dialogo
				Shell nuevo = new Shell(shell, SWT.DIALOG_TRIM);

				// Caracter�sticas de la Ventana "Nuevo"
				nuevo.setSize(300, 120);
				nuevo.setText("Nuevo");

				// Creaci�n de Tabla para mostrar la Matriz en el folder
				Table table = new Table(folder, SWT.BORDER | SWT.MULTI);

				// Layout
				nuevo.setLayout(new GridLayout(3, true));
				GridData gridData = new GridData(GridData.CENTER);
				gridData.horizontalSpan = 2;

				// Creaci�n del Label y Text para pedir las nFilas
				Label labelFilas = new Label(nuevo, SWT.LEFT);
				labelFilas.setText("Introduzca el n�mero de Filas: ");
				Text textFilas = new Text(nuevo, SWT.SINGLE | SWT.BORDER);
				textFilas.setLayoutData(gridData);
				textFilas.computeSize(5, 5);

				// Creaci�n del Label y Text para pedir las nColumnas
				Label labelColumnas = new Label(nuevo, SWT.LEFT);
				labelColumnas.setText("Introduzca el n�mero de Columnas: ");
				Text textColumnas = new Text(nuevo, SWT.SINGLE | SWT.BORDER);
				textColumnas.setLayoutData(gridData);

				// Creaci�n de Button para aceptar nFilas y nColumnas
				Button buttonAceptar = new Button(nuevo, SWT.PUSH);
				buttonAceptar.setText("Aceptar");
				buttonAceptar.addSelectionListener(new SelectionListener() {
					public void widgetSelected(SelectionEvent event) {
						int nFilas = Integer.parseInt(textFilas.getText());
						int nColumnas = Integer.parseInt(textColumnas.getText());
						obj.crear(nFilas, nColumnas);

						// Creaci�n de Columnas
						for (int j = 0; j < obj.getColumnas(); j++) {
							TableColumn column = new TableColumn(table,
									SWT.NONE);
							column.setText((Integer.toString(j)));
							column.setWidth(30);
						}

						// Creaci�n de Filas, que se rellenan con la Matriz
						// creada
						for (int i = 0; i < obj.getFilas(); i++) {
							TableItem item = new TableItem(table, SWT.NONE);
							for (int j = 0; j < obj.getFilas(); j++) {
								item.setText(j,
										Integer.toString(obj.getMatriz(i, j)));
							}
						}

						// A�adimos en nuestros vectores los datos que queremos
						// retornar
						arrayListMatriz.add(obj.getMatrizEntera());
						arrayListFilas.add(obj.getFilas());
						arrayListColumnas.add(obj.getColumnas());

						// Creaci�n del Item de la Tabla "Arbol" tableMatrices
						TableItem itemMatrices = new TableItem(tableMatrices,
								SWT.NONE);
						itemMatrices.setText("Nueva Matriz "
								+ arrayListMatriz.size());

						// Creaci�n del Item para el CTabItem, contiene un
						// �ndice que indica el n�mero de Matriz
						CTabItem item = new CTabItem(folder, SWT.NONE);
						item.setText("Matriz " + arrayListMatriz.size());
						item.setControl(table);

						nuevo.close();
					}

					public void widgetDefaultSelected(SelectionEvent event) {
					}
				});

				// Creaci�n de Button para cancelar nFilas y nColumnas
				Button buttonCancelar = new Button(nuevo, SWT.PUSH);
				buttonCancelar.setText("Cancelar");
				buttonCancelar.pack();
				buttonCancelar.addSelectionListener(new SelectionListener() {
					public void widgetSelected(SelectionEvent event) {
						nuevo.close();
					}

					public void widgetDefaultSelected(SelectionEvent event) {

					}
				});

				nuevo.open();
			}

			public void widgetDefaultSelected(SelectionEvent event) {
			}
		}
		// Funcion abrir, Abre una Matriz ya existente
		class abrir implements SelectionListener {
			public void widgetSelected(SelectionEvent event) {
				FileDialog fd = new FileDialog(shell, SWT.OPEN | SWT.CLOSE);
				fd.setFilterPath("C:/");
				String[] filterExt = { "*.txt", "*.*" };
				fd.setFilterExtensions(filterExt);
				String fichero = fd.open();
				if (fichero != null) {
					obj.leer(fichero);

					// Creaci�n de Tabla
					Table table = new Table(folder, SWT.BORDER | SWT.MULTI);

					// Creaci�n de Columnas
					for (int j = 0; j < obj.getColumnas(); j++) {
						TableColumn column = new TableColumn(table, SWT.NONE);
						column.setText((Integer.toString(j)));
						column.setWidth(30);
					}

					// Creaci�n de la Matriz
					for (int i = 0; i < obj.getFilas(); i++) {
						TableItem item = new TableItem(table, SWT.NONE);
						for (int j = 0; j < obj.getColumnas(); j++) {
							item.setText(j,
									Integer.toString(obj.getMatriz(i, j)));
						}
					}

					// Creaci�n del Item de la Tabla "Arbol" tableMatrices
					TableItem itemMatrices = new TableItem(tableMatrices,
							SWT.NONE);
					itemMatrices
							.setText("Matriz cargada en la ruta " + fichero);

					// Creaci�n del Item del TabItem
					CTabItem item = new CTabItem(folder, SWT.CLOSE);
					item.setText(fichero);
					item.setControl(table);

					// A�adimos en nuestros vectores los datos que queremos
					// retornar
					arrayListMatriz.add(obj.getMatrizEntera());
					arrayListFilas.add(obj.getFilas());
					arrayListColumnas.add(obj.getColumnas());

				}

			}

			public void widgetDefaultSelected(SelectionEvent event) {
			}
		}
		// Funcion guardar, Guarda una Matriz
		class guardar implements SelectionListener {
			public void widgetSelected(SelectionEvent event) {
				FileDialog fd = new FileDialog(shell, SWT.SAVE);
				fd.setText("Guardar");
				fd.setFilterPath("C:/");
				String[] filterExt = { "*.txt", "*.*" };
				fd.setFilterExtensions(filterExt);
				String fichero = fd.open();
				obj.guardar(fichero);
			}

			public void widgetDefaultSelected(SelectionEvent event) {
			}
		}
		// Funcion salir, Sale de la Aplicaci�n
		class salir implements SelectionListener {
			public void widgetSelected(SelectionEvent event) {
				MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION
						| SWT.YES | SWT.NO);
				messageBox.setMessage(" Seguro que desea Salir?");
				messageBox.setText("Salir");
				int response = messageBox.open();
				if (response == SWT.YES)
					System.exit(0);
			}

			public void widgetDefaultSelected(SelectionEvent event) {
			}
		}

		// Funcion parametros, Abre una ventana mostrando los Par�metros
		class estadisticas implements SelectionListener {
			public void widgetSelected(SelectionEvent event) {
				// Creaci�n de una Shell hija llamada "estad�sticas" para el
				// nuevo dialogo
				Shell estadisticas = new Shell(shell, SWT.DIALOG_TRIM);

				// Caracter�sticas de la Ventana "Nuevo"
				estadisticas.setText("Estad�sticas");
				estadisticas.setSize(160, 150);

				// Layout
				GridLayout gridLayout = new GridLayout();
				gridLayout.numColumns = 2;
				gridLayout.makeColumnsEqualWidth = true;
				estadisticas.setLayout(gridLayout);

				new Label(estadisticas, SWT.NONE).setText("N�mero de Filas: ");
				new Label(estadisticas, SWT.NONE).setText(Integer.toString(obj
						.getFilas()));
				new Label(estadisticas, SWT.NONE)
						.setText("N�mero de Columnas: ");
				new Label(estadisticas, SWT.NONE).setText(Integer.toString(obj
						.getColumnas()));
				new Label(estadisticas, SWT.NONE).setText("N�mero M�nimo: ");
				new Label(estadisticas, SWT.NONE).setText(Integer.toString(obj
						.getMinimo()));
				new Label(estadisticas, SWT.NONE).setText("N�mero M�ximo: ");
				new Label(estadisticas, SWT.NONE).setText(Integer.toString(obj
						.getMaximo()));

				// Creaci�n de Button para volver a la pantalla principal
				Button buttonCancelar = new Button(estadisticas, SWT.PUSH);
				buttonCancelar.setText("Volver");
				buttonCancelar.setLayoutData(new GridData(
						GridData.VERTICAL_ALIGN_CENTER));
				buttonCancelar.addSelectionListener(new SelectionListener() {
					public void widgetSelected(SelectionEvent event) {
						estadisticas.close();
					}

					public void widgetDefaultSelected(SelectionEvent event) {

					}
				});

				estadisticas.open();

			}

			public void widgetDefaultSelected(SelectionEvent event) {
			}
		}

		// Asignaci�n de Funciones de menuArchivo
		menuArchivoNuevo.addSelectionListener(new nuevo());
		menuArchivoAbrir.addSelectionListener(new abrir());
		menuArchivoGuardar.addSelectionListener(new guardar());
		menuArchivoSalir.addSelectionListener(new salir());

		// Asignaci�n de Funciones de menuVisualizaci�n
		menuVisualizacionEstadisticas.addSelectionListener(new estadisticas());

		shell.setMenuBar(menuBar);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

	public static void main(String args[]) {
		Interfaz obj = new Interfaz();
		obj.menu();

	}

}