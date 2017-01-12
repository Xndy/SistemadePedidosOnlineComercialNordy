--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6beta1
-- Dumped by pg_dump version 9.6beta1

-- Started on 2016-08-30 22:18:47

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12387)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2179 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 185 (class 1259 OID 90126)
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE categoria (
    idcategoria integer NOT NULL,
    descripcion character varying(250)
);


ALTER TABLE categoria OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 90129)
-- Name: categoria_idcategoria_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE categoria_idcategoria_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE categoria_idcategoria_seq OWNER TO postgres;

--
-- TOC entry 2180 (class 0 OID 0)
-- Dependencies: 186
-- Name: categoria_idcategoria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE categoria_idcategoria_seq OWNED BY categoria.idcategoria;


--
-- TOC entry 187 (class 1259 OID 90131)
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cliente (
    idcliente integer NOT NULL,
    nombre character varying(250),
    celular character varying(10),
    cedula character varying(10),
    direccion character varying(250),
    correo character varying(100),
    estado character varying(50),
    contrasenia character varying,
    tipo character varying
);


ALTER TABLE cliente OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 90137)
-- Name: cliente_idcliente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cliente_idcliente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cliente_idcliente_seq OWNER TO postgres;

--
-- TOC entry 2181 (class 0 OID 0)
-- Dependencies: 188
-- Name: cliente_idcliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cliente_idcliente_seq OWNED BY cliente.idcliente;


--
-- TOC entry 189 (class 1259 OID 90139)
-- Name: detalle_compra; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE detalle_compra (
    iddetalle integer NOT NULL,
    codproductos integer,
    cantidad integer,
    precio_unitario double precision,
    precio_total double precision,
    idproforma integer
);


ALTER TABLE detalle_compra OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 90142)
-- Name: detalle_compra_iddetalle_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE detalle_compra_iddetalle_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE detalle_compra_iddetalle_seq OWNER TO postgres;

--
-- TOC entry 2182 (class 0 OID 0)
-- Dependencies: 190
-- Name: detalle_compra_iddetalle_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE detalle_compra_iddetalle_seq OWNED BY detalle_compra.iddetalle;


--
-- TOC entry 191 (class 1259 OID 90144)
-- Name: productos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE productos (
    idproductos integer NOT NULL,
    codcategoria integer,
    cantidad integer,
    talla character varying(15),
    color character varying(25),
    detalle character varying(250),
    precio double precision,
    imagen bytea
);


ALTER TABLE productos OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 90147)
-- Name: productos_idproductos_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE productos_idproductos_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE productos_idproductos_seq OWNER TO postgres;

--
-- TOC entry 2183 (class 0 OID 0)
-- Dependencies: 192
-- Name: productos_idproductos_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE productos_idproductos_seq OWNED BY productos.idproductos;


--
-- TOC entry 193 (class 1259 OID 90149)
-- Name: proforma; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE proforma (
    idproforma integer NOT NULL,
    codcliente integer,
    fechaentrega date,
    subtotal double precision,
    iva double precision,
    descuento double precision,
    estado boolean
);


ALTER TABLE proforma OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 90152)
-- Name: proforma_idproforma_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE proforma_idproforma_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE proforma_idproforma_seq OWNER TO postgres;

--
-- TOC entry 2184 (class 0 OID 0)
-- Dependencies: 194
-- Name: proforma_idproforma_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE proforma_idproforma_seq OWNED BY proforma.idproforma;


--
-- TOC entry 2027 (class 2604 OID 90154)
-- Name: categoria idcategoria; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY categoria ALTER COLUMN idcategoria SET DEFAULT nextval('categoria_idcategoria_seq'::regclass);


--
-- TOC entry 2028 (class 2604 OID 90155)
-- Name: cliente idcliente; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente ALTER COLUMN idcliente SET DEFAULT nextval('cliente_idcliente_seq'::regclass);


--
-- TOC entry 2029 (class 2604 OID 90156)
-- Name: detalle_compra iddetalle; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY detalle_compra ALTER COLUMN iddetalle SET DEFAULT nextval('detalle_compra_iddetalle_seq'::regclass);


--
-- TOC entry 2030 (class 2604 OID 90157)
-- Name: productos idproductos; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY productos ALTER COLUMN idproductos SET DEFAULT nextval('productos_idproductos_seq'::regclass);


--
-- TOC entry 2031 (class 2604 OID 90158)
-- Name: proforma idproforma; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proforma ALTER COLUMN idproforma SET DEFAULT nextval('proforma_idproforma_seq'::regclass);


--
-- TOC entry 2163 (class 0 OID 90126)
-- Dependencies: 185
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY categoria (idcategoria, descripcion) FROM stdin;
1	Cinturones
2	Hebillas
3	Billeteras
4	Mochilas
5	Canguros
6	Tirantes
\.


--
-- TOC entry 2185 (class 0 OID 0)
-- Dependencies: 186
-- Name: categoria_idcategoria_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('categoria_idcategoria_seq', 6, true);


--
-- TOC entry 2165 (class 0 OID 90131)
-- Dependencies: 187
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cliente (idcliente, nombre, celular, cedula, direccion, correo, estado, contrasenia, tipo) FROM stdin;
20	Andy Sanchez	0969351982	0946598713	Ecuador	andyaugustosanchezgonzalez@gmail.com	Activo	123456	Administrador
21	Luis Llaque	0985321478	0985176416	Mapasingue	lllaque91@gmail.com	Deudor	4321	Cliente
2	Pablo Vélez	0841772535	0922453198	6 de Marzo y 9 de Octubre	pave75@gmail.com	Deudor	0922453198	Cliente
19	Xndy	0876913465	0963578521	6 de Marzo y 9 de Octubre	andysanchezgonzalez199@gmail.com	No deudor	1234	Cliente
1	Xndyer	0952619912	0954224121	Quito	pedro.a.v@hotmail.com	Deudor	123466	Cliente
8	Humberto	0897643152	0932154678	aqui	humcoal@gmail.com	\N	0932154678	Cliente
3	Julio Almeida	0954683791	0894563214	Manta	julalm70@hotmail.com	Deudor	0894563214	Cliente
4	Jaime Rodríguez	0984542158	0931197864	Ambato	jaime-ro-as@outlook.es	Deudor	0931197864	Cliente
5	Marcos Arguello	0874953541	0945621187	Ibarra	marag_tu@gmail.com	No Deudor	0945621187	Cliente
6	Andrés Alcívar	0954632978	0963258741	Av. Quito y Quisquis	acab@hotmail.com	Deudor	0963258741	Cliente
9	Fabricio Tutiven	0956389714	0951357852	Loja	fabri_tc@outlook.es	No deudor	0951357852	Cliente
7	César Vergara	0979561234	0987456321	Registro Civíl del sur	cesar_vergara85@hotmail.com	Activo	0987456321	Cliente
10	Fabricio Tito	0845671234	0952831647	Cuenca	fab_ti_ch@gmail.com	Deudor	0952831647	Cliente
11	Christian Soledispa	0987652314	0954123987	Av. de las Américas	kike_sole90@hotmail.com	No deudor	0954123987	Cliente
12	Sergio Cantos	0878496632	0985467231	Manta	sergi.can.mak@hotmail.com	Deudor	0985467231	Cliente
13	Ángel Alvarado	0973829146	0875361945	Av. 9 de Octubre y Lorenzo de Garaicoa	angelevil85@hotmail.com	Deudor	0875361945	Cliente
15	Carlos Jara	0993195473	0978456132	Loja	carljara@outlook.es	Deudor	0978456132	Cliente
14	Eduardo Jaramillo	0963245197	0876953419	Mapasingue Este Av. Primera	edu_jar_and@gmail.com	No deudor	0876953419	Cliente
16	José Roblez	0889453793	0879653146	Cuenca	josrob@hotmail.com	Deudor	0879653146	Cliente
17	Emilio Carrión	0980349765	0896351978	Imbabura	emilcarr@gmail.com	Deudor	0896351978	Cliente
18	Julio Cortés	0849663891	0945643127	Aguirre y Sta. Elena	julcor@hotmail.com	Deudor	0945643127	Cliente
\.


--
-- TOC entry 2186 (class 0 OID 0)
-- Dependencies: 188
-- Name: cliente_idcliente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cliente_idcliente_seq', 21, true);


--
-- TOC entry 2167 (class 0 OID 90139)
-- Dependencies: 189
-- Data for Name: detalle_compra; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY detalle_compra (iddetalle, codproductos, cantidad, precio_unitario, precio_total, idproforma) FROM stdin;
\.


--
-- TOC entry 2187 (class 0 OID 0)
-- Dependencies: 190
-- Name: detalle_compra_iddetalle_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('detalle_compra_iddetalle_seq', 28, true);


--
-- TOC entry 2169 (class 0 OID 90144)
-- Dependencies: 191
-- Data for Name: productos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY productos (idproductos, codcategoria, cantidad, talla, color, detalle, precio, imagen) FROM stdin;
9	2	5205	40	ninguno	rodillo	0.55000000000000004	\N
10	3	150	ninguno	café	cobra	1	\N
11	6	100	adulto	varios colores	adulto	0.75	\N
12	5	200	ninguno	varios colores	ninguno	2	\N
13	6	100	niño	varios colores	niño	0.5	\N
14	1	1000	35	miel	fibra rodillo	1.25	\N
15	1	1500	35	café	fibra rodillo	1.25	\N
16	1	850	35	miel	fibra pupo	1.25	\N
17	1	795	35	café	fibra pupo	1.25	\N
1	1	800	40	miel	fibra rodillo	1.75	\N
2	1	950	40	café	fibra rodillo	1.75	\N
3	1	900	40	miel	fibra pupo	1.75	\N
4	1	500	40	café	fibra pupo	1.75	\N
5	3	11	ninguno	Plomas	Billeteras	22	\N
6	3	77	ninguno	Anaranjadas	Billeteras	14	\N
7	4	79	ninguno	Varios Colores	Mochilas	15	\N
8	2	500	ninguno	miel	cuero diesel	15	\N
18	1	500	25	miel	fibra rodillo	1	\N
19	1	352	25	café	fibra rodillo	1	\N
20	\N	1000	25	miel	fibra pupo	1	\N
21	\N	99	25	café	fibra pupo	1	\N
\.


--
-- TOC entry 2188 (class 0 OID 0)
-- Dependencies: 192
-- Name: productos_idproductos_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('productos_idproductos_seq', 21, true);


--
-- TOC entry 2171 (class 0 OID 90149)
-- Dependencies: 193
-- Data for Name: proforma; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY proforma (idproforma, codcliente, fechaentrega, subtotal, iva, descuento, estado) FROM stdin;
\.


--
-- TOC entry 2189 (class 0 OID 0)
-- Dependencies: 194
-- Name: proforma_idproforma_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('proforma_idproforma_seq', 44, true);


--
-- TOC entry 2033 (class 2606 OID 90160)
-- Name: categoria categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (idcategoria);


--
-- TOC entry 2035 (class 2606 OID 90162)
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (idcliente);


--
-- TOC entry 2037 (class 2606 OID 90164)
-- Name: detalle_compra detalle_compra_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY detalle_compra
    ADD CONSTRAINT detalle_compra_pkey PRIMARY KEY (iddetalle);


--
-- TOC entry 2039 (class 2606 OID 90166)
-- Name: productos productos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY productos
    ADD CONSTRAINT productos_pkey PRIMARY KEY (idproductos);


--
-- TOC entry 2041 (class 2606 OID 90168)
-- Name: proforma proforma_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proforma
    ADD CONSTRAINT proforma_pkey PRIMARY KEY (idproforma);


--
-- TOC entry 2042 (class 2606 OID 90169)
-- Name: detalle_compra detalle_compra_codproductos_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY detalle_compra
    ADD CONSTRAINT detalle_compra_codproductos_fkey FOREIGN KEY (codproductos) REFERENCES productos(idproductos);


--
-- TOC entry 2043 (class 2606 OID 90174)
-- Name: detalle_compra detalle_compra_idproforma_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY detalle_compra
    ADD CONSTRAINT detalle_compra_idproforma_fkey FOREIGN KEY (idproforma) REFERENCES proforma(idproforma);


--
-- TOC entry 2044 (class 2606 OID 90179)
-- Name: productos productos_codcategoria_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY productos
    ADD CONSTRAINT productos_codcategoria_fkey FOREIGN KEY (codcategoria) REFERENCES categoria(idcategoria);


--
-- TOC entry 2045 (class 2606 OID 90184)
-- Name: proforma proforma_codcliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proforma
    ADD CONSTRAINT proforma_codcliente_fkey FOREIGN KEY (codcliente) REFERENCES cliente(idcliente);


-- Completed on 2016-08-30 22:18:49

--
-- PostgreSQL database dump complete
--

