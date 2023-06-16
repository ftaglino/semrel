# semrel
Semrel is a Java library providing methods for computing semantic relatedness between nodes in an RDF knowledge graph.

The SemRel library has been tested under Java 1.8 or later.

In the current version, ten approaches from the Literature have been implemented.
Some of these approaches provide more than one strategy, that is more variants to actually compute semantic relatedness.

In [1], these approaches were described with the support of running examples, compared through an experiment, and organized according to the following three categories:

**Approaches based on adjacent resources**, which consider nodes that are directly linked to those compared (i.e., adjacent resources). They are; 
- _Wikipedia Link-based Measure_ (_WLM_) [2]
- _Linked Open Data Description Overlap_ (_LODDO_) [3], providing 2 strategies, namely, _lod_overlap_, and _lod_jaccard_.

**Approaches based on triple patterns**, which rely on nodes that belong to paths satisfying specific conditions. They are: 
- _Linked Data Semantic Distance_ (_LDSD_) [4], providing 3 strategies, namely, _ldsd_dw_, _ldsd_iw_, _ldsd_cw_;
- _Linked Data Semantic Distance with Global Normalization_ (_LDSDGN_) [5], providing 3 strategies, namely, _ldsd_alpha_, _ldsd_beta_, _ldsd_gamma_;
- _Propagated Linked Data Semantic Distance_ (_PLDSD_) [6].

**Approaches based on triple weights**, which require weighing the triples that belong to the paths linking the compared resources to be computed. They are: 
- _Information Content-based Measure_ (_ICM_) [7], providing three strategies, namely, _combIC_, _jointIC_, and _IC+PMI_;
- _REWOrD_ [8], providing 5 stragegies, namely, _reword_in_, _reword_out_, _average_, _reword_mip_, and _reword_;
- _Exclusivity-based Measure_ (_ExclM_) [9];
- _ASRMPm_ [10], providing three strategies, namely, _ASRMP_a_, _ASRMP_b_, and _ASRMP_c_;
- _Proximity-based Method_ (_ProxM_) [11].


**Bibliography**

[1] A. Formica, F. Taglino, Semantic relatedness in DBpedia: A comparative and experimental assessment, Information Sciences 621 (2023) 474–505. doi:https://doi.org/10.1016/j.ins.2022.11.025.

[2] I. H. Witten, D. Milne, An effective, low-cost measure of semantic relatedness obtained from Wikipedia links, in: Proceeding of AAAI Workshop on Wikipedia and Artificial Intelligence: an Evolving Synergy,AAAI Press, Chicago, USA, 2008, pp. 25–30.

[3] W. Zhou, H. Wang, J. Chao, W. Zhang, Y. Yu, LODDO: Using Linked Open Data Description Overlap to Measure Semantic Relatedness between Named Entities, Springer Berlin Heidelberg, Berlin, Heidelberg, 2012, pp. 268–283.

[4] A. Passant, Measuring semantic distance on linking data and using it for resources recommendations, in: AAAI Spring Symposium: Linked Data Meets Artificial Intelligence, 2010.

[5] G. Piao, J. G. Breslin, Measuring semantic distance for linked open data-enabled recommender systems, in: Proceedings of the 31st Annual ACM Symposium on Applied Computing, SAC ’16, ACM, New York, NY, USA, 2016, pp. 315–320.

[6] S. Alfarhood, K. Labille, S. Gauch, PLDSD: Propagated Linked Data Semantic Distance, in: IEEE 26th Int. Conference on Enabling Technologies: Infrastructure for Collaborative Enterprises, WETICE ’17, 2017, pp. 278–283.

[7] M. Schuhmacher, S. P. Ponzetto, Knowledge-based graph document modeling, in: Proceedings of the 7th ACM International Conference on Web Search and Data Mining, WSDM ’14, ACM, New York, NY, USA, 2014, pp. 543–552.

[8] G. Pirr ́o, REWOrD: Semantic Relatedness in the Web of Data, in: AAAI Conference on Artificial Intelligence, 2012.

[9] I. Hulpu ̧s, N. Prangnawarat, C. Hayes, Path-Based Semantic Relatedness on Linked Data and Its Use to Word and Entity Disambiguation, Springer International Publishing, Cham, 2015, pp. 442–457.

[10] C. B. El Vaigh, F. Goasdou ́e, G. Gravier, P. S ́ebillot, A novel path-based entity relatedness measure for efficient collective entity linking, in: J. Z. Pan, V. Tamma, C. d’Amato, K. Janowicz, B. Fu, A. Polleres, O. Seneviratne, L. Kagal (Eds.), The Semantic Web – ISWC 2020, Springer International Publishing, Cham, 2020, pp. 164–182.

[11] J. P. Leal, Using proximity to compute semantic relatedness in RDF graphs, Comput. Sci. Inf. Syst. 10 (4) (2013) 1727–1746.
