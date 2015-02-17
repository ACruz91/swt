package matriz;

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
	int indice;

	public void menu() {
		// Parámetros
		Menu menuBar, menuArchivo, menuVisualizacion;
		MenuItem menuArchivoCabecera, menuVisualizacionCabecera;
		MenuItem menuArchivoNuevo, menuArchivoGuardar, menuArchivoSalir, menuArchivoAbrir, menuVisualizacionEstadisticas;
		Matriz obj = new Matriz();
		indice = 0;

		// Creación del display y shell
		Display display = new Display();
		Shell shell = new Shell(display);

		// Parámetros Ventana
		shell.setText("Ejercicio SWT");
		shell.setMaximized(true);

		// FillLayout
		FillLayout fillLayout = new FillLayout(SWT.HORIZONTAL);
		fillLayout.marginWidth = 5;
		fillLayout.marginHeight = 5;
		fillLayout.spacing = 50;
		shell.setLayout(fillLayout);

		// Creación de una Tabla que será el "Arbol" de las Matrices
		Table tableMatrices = new Table(shell, SWT.BORDER);
		tableMatrices.setHeaderVisible(true);
		tableMatrices.setLinesVisible(true);
		TableColumn column1 = new TableColumn(tableMatrices, SWT.NONE);
		column1.setText("Columna");
		column1.setWidth(700);

		// Creación del CTabFolder para las pestañas
		CTabFolder folder = new CTabFolder(shell, SWT.BORDER);

		// Creación del Menú
		menuBar = new Menu(shell, SWT.BAR);

		// Creación de la Cabecera del menuBar, Archivo
		menuArchivoCabecera = new MenuItem(menuBar, SWT.CASCADE);
		menuArchivoCabecera.setText("&Archivo");

		// Creación de la Cabecera del menuBar, Archivo
		menuVisualizacionCabecera = new MenuItem(menuBar, SWT.CASCADE);
		menuVisualizacionCabecera.setText("&Visualización");

		// Creación del subMenú Archivo es una cabecera del menú menuBar
		menuArchivo = new Menu(shell, SWT.DROP_DOWN);
		menuArchivoCabecera.setMenu(menuArchivo);

		// Creación de un Item del Submenú Archivo, Nuevo
		menuArchivoNuevo = new MenuItem(menuArchivo, SWT.PUSH);
		menuArchivoNuevo.setText("&Nuevo\t CTRL+N");
		menuArchivoNuevo.setAccelerator(SWT.CTRL + 'N');

		// Creación de un Item del Submenú Archivo, Guardar
		menuArchivoAbrir = new MenuItem(menuArchivo, SWT.PUSH);
		menuArchivoAbrir.setText("&Abrir\t CTRL+O");
		menuArchivoAbrir.setAccelerator(SWT.CTRL + 'O');

		// Creación de un Item del Submenú Archivo, Guardar
		menuArchivoGuardar = new MenuItem(menuArchivo, SWT.PUSH);
		menuArchivoGuardar.setText("&Guardar\t CTRL+S");
		menuArchivoGuardar.setAccelerator(SWT.CTRL + 'S');

		// Creación de un Item del Submenú Archivo, Salir
		menuArchivoSalir = new MenuItem(menuArchivo, SWT.PUSH);
		menuArchivoSalir.setText("&Salir\t CTRL+X");
		menuArchivoSalir.setAccelerator(SWT.CTRL + 'X');

		// Creación del subMenú Visualización es una cabecera del menú menuBar
		menuVisualizacion = new Menu(shell, SWT.DROP_DOWN);
		menuVisualizacionCabecera.setMenu(menuVisualizacion);

		// Creación de un Item del Submenú Visualización, Estadísticas
		menuVisualizacionEstadisticas = new MenuItem(menuVisualizacion,
				SWT.PUSH);
		menuVisualizacionEstadisticas.setText("&Estadísticas\t CTRL+N");
		menuVisualizacionEstadisticas.setAccelerator(SWT.CTRL + 'E');

		// Funciones

		// Funcion nuevo, Crea una nueva Matriz
		class nuevo implements SelectionListener {
			public void widgetSelected(SelectionEvent event) {
				// Creación de una Shell hija llamada "nuevo" para el nuevo
				// dialogo
				Shell nuevo = new Shell(shell, SWT.DIALOG_TRIM);

				// Características de la Ventana "Nuevo"
				nuevo.setSize(300, 120);
				nuevo.setText("Nuevo");

				// Creación de Tabla para mostrar la Matriz en el folder
				Table table = new Table(folder, SWT.BORDER | SWT.MULTI);

				// Layout
				nuevo.setLayout(new GridLayout(3, true));
				GridData gridData = new GridData(GridData.CENTER);
				gridData.horizontalSpan = 2;

				// Creación del Label y Text para pedir las nFilas
				Label labelFilas = new Label(nuevo, SWT.LEFT);
				labelFilas.setText("Introduzca el número de Filas: ");
				Text textFilas = new Text(nuevo, SWT.SINGLE | SWT.BORDER);
				textFilas.setLayoutData(gridData);
				textFilas.computeSize(5, 5);

				// Creación del Label y Text para pedir las nColumnas
				Label labelColumnas = new Label(nuevo, SWT.LEFT);
				labelColumnas.setText("Introduzca el número de Columnas: ");
				Text textColumnas = new Text(nuevo, SWT.SINGLE | SWT.BORDER);
				textColumnas.setLayoutData(gridData);

				// Creación de Button para aceptar nFilas y nColumnas
				Button buttonAceptar = new Button(nuevo, SWT.PUSH);
				buttonAceptar.setText("Aceptar");
				buttonAceptar.addSelectionListener(new SelectionListener() {
					public void widgetSelected(SelectionEvent event) {
						int nFilas = Integer.parseInt(textFilas.getText());
						int nColumnas = Integer.parseInt(textColumnas.getText());
						obj.crear(nFilas, nColumnas);

						// Creación de Columnas
						for (int j = 0; j < nColumnas; j++) {
							TableColumn column = new TableColumn(table,
									SWT.NONE);
							column.setText((Integer.toString(j)));
							column.setWidth(30);
						}

						// Creación de Filas, que se rellenan con la Matriz
						// creada
						for (int i = 0; i < nFilas; i++) {
							TableItem item = new TableItem(table, SWT.NONE);
							for (int j = 0; j < nColumnas; j++) {
								item.setText(j,
										Integer.toString(obj.getMatriz(i, j)));
							}
						}

						// Creación del Item de la Tabla "Arbol" tableMatrices
						TableItem itemMatrices = new TableItem(tableMatrices,
								SWT.NONE);
						itemMatrices.setText("Nueva Matriz " + indice);

						// Creación del Item para el CTabItem, contiene un
						// índice que indica el número de Matriz
						CTabItem item = new CTabItem(folder, SWT.CLOSE);
						item.setText("Matriz " + indice);
						item.setControl(table);
						
						// Se incrementa el índice después de una nueva creación
						indice++;
						nuevo.close();
					}

					public void widgetDefaultSelected(SelectionEvent event) {
					}
				});

				// Creación de Button para cancelar nFilas y nColumnas
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

					// Creación de Tabla
					Table table = new Table(folder, SWT.BORDER | SWT.MULTI);

					// Creación de Columnas
					for (int j = 0; j < obj.getColumnas(); j++) {
						TableColumn column = new TableColumn(table, SWT.NONE);
						column.setText((Integer.toString(j)));
						column.setWidth(30);
					}

					// Creación de la Matriz
					for (int i = 0; i < obj.getFilas(); i++) {
						TableItem item = new TableItem(table, SWT.NONE);
						for (int j = 0; j < obj.getColumnas(); j++) {
							item.setText(j,
									Integer.toString(obj.getMatriz(i, j)));
						}
					}

					// Creación del Item de la Tabla "Arbol" tableMatrices
					indice++;
					TableItem itemMatrices = new TableItem(tableMatrices,
							SWT.NONE);
					itemMatrices.setText("Matriz cargada en la ruta " + fichero);

					// Creación del Item del TabItem
					CTabItem item = new CTabItem(folder, SWT.CLOSE);
					item.setText(fichero);
					item.setControl(table);

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
		// Funcion salir, Sale de la Aplicación
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

		// Funcion parametros, Abre una ventana mostrando los Parámetros
		class estadisticas implements SelectionListener {
			public void widgetSelected(SelectionEvent event) {
				// Creación de una Shell hija llamada "estadísticas" para el
				// nuevo dialogo
				Shell estadisticas = new Shell(shell, SWT.DIALOG_TRIM);

				// Características de la Ventana "Nuevo"
				estadisticas.setText("Estadísticas");
				estadisticas.setSize(160, 150);

				// Layout
				GridLayout gridLayout = new GridLayout();
				gridLayout.numColumns = 2;
				gridLayout.makeColumnsEqualWidth = true;
				estadisticas.setLayout(gridLayout);

				new Label(estadisticas, SWT.NONE).setText("Número de Filas: ");
				new Label(estadisticas, SWT.NONE).setText(Integer.toString(obj
						.getFilas()));
				new Label(estadisticas, SWT.NONE)
				.setText("Número de Columnas: ");
				new Label(estadisticas, SWT.NONE).setText(Integer.toString(obj
						.getColumnas()));
				new Label(estadisticas, SWT.NONE).setText("Número Mínimo: ");
				new Label(estadisticas, SWT.NONE).setText(Integer.toString(obj
						.getMinimo()));
				new Label(estadisticas, SWT.NONE).setText("Número Máximo: ");
				new Label(estadisticas, SWT.NONE).setText(Integer.toString(obj
						.getMaximo()));

				// Creación de Button para volver a la pantalla principal
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

		// Asignación de Funciones de menuArchivo
		menuArchivoNuevo.addSelectionListener(new nuevo());
		menuArchivoAbrir.addSelectionListener(new abrir());
		menuArchivoGuardar.addSelectionListener(new guardar());
		menuArchivoSalir.addSelectionListener(new salir());

		// Asignación de Funciones de menuVisualización
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