package ca.qc.cstj.cleanarchitecture.data

import ca.qc.cstj.cleanarchitecture.models.MeditationCategory
import ca.qc.cstj.cleanarchitecture.models.MeditationSession

object MockData {
    val meditationSessions = listOf(
        MeditationSession(
            title = "Nuit Paisible",
            subtitle = "Endormissement rapide",
            with = "Yannick",
            category = MeditationCategory.SLEEP,
            tags = listOf("Guidée", "Sommeil", "Débutant"),
            durationInMinutes = 25,
            isPremium = false
        ),
        MeditationSession(
            title = "Deep Work",
            subtitle = "Concentration intense",
            with = "Maël",
            category = MeditationCategory.FOCUS,
            tags = listOf("Travail", "Musique", "Productivité"),
            durationInMinutes = 45,
            isPremium = true
        ),
        MeditationSession(
            title = "Pause Café",
            subtitle = "Respiration courte",
            with = "Aude",
            category = MeditationCategory.RELAX,
            tags = listOf("Respiration", "Débutant", "Anxiété"),
            durationInMinutes = 5,
            isPremium = false
        ),
        MeditationSession(
            title = "Lâcher Prise",
            subtitle = "Après le travail",
            with = "Alain B.",
            category = MeditationCategory.RELAX,
            tags = listOf("Guidée", "Stress", "Soirée"),
            durationInMinutes = 15,
            isPremium = false
        ),
        MeditationSession(
            title = "Train de Nuit",
            subtitle = "Voyage imaginaire",
            with = "Joël",
            category = MeditationCategory.SLEEP,
            tags = listOf("Visualisation", "Histoire", "Sommeil"),
            durationInMinutes = 30,
            isPremium = true
        ),
        MeditationSession(
            title = "Avant l'Examen",
            subtitle = "Calme mental",
            with = "Houssem",
            category = MeditationCategory.FOCUS,
            tags = listOf("Stress", "Études", "Confiance"),
            durationInMinutes = 10,
            isPremium = false
        ),
        MeditationSession(
            title = "Marche en Forêt",
            subtitle = "Sons de la nature",
            with = "Frédéric",
            category = MeditationCategory.RELAX,
            tags = listOf("Nature", "Musique", "Calme"),
            durationInMinutes = 20,
            isPremium = true
        ),
        MeditationSession(
            title = "Sous la Pluie",
            subtitle = "Bruit blanc apaisant",
            with = "Éric",
            category = MeditationCategory.SLEEP,
            tags = listOf("Nature", "Pluie", "Sommeil"),
            durationInMinutes = 60,
            isPremium = false
        ),
        MeditationSession(
            title = "Code Flow",
            subtitle = "Immersion totale",
            with = "Alain M.",
            category = MeditationCategory.FOCUS,
            tags = listOf("Musique", "Travail", "Binaural"),
            durationInMinutes = 50,
            isPremium = true
        ),
        MeditationSession(
            title = "Respiration Carrée",
            subtitle = "Technique anti-stress",
            with = "Aude",
            category = MeditationCategory.RELAX,
            tags = listOf("Respiration", "Technique", "Débutant"),
            durationInMinutes = 8,
            isPremium = false
        ),
        MeditationSession(
            title = "Corps Lourd",
            subtitle = "Body Scan relaxant",
            with = "Frédéric",
            category = MeditationCategory.SLEEP,
            tags = listOf("Corps", "Guidée", "Détente"),
            durationInMinutes = 25,
            isPremium = false
        ),
        MeditationSession(
            title = "Réveil Énergique",
            subtitle = "Matin dynamique",
            with = "Yannick",
            category = MeditationCategory.FOCUS,
            tags = listOf("Matin", "Énergie", "Guidée"),
            durationInMinutes = 12,
            isPremium = false
        ),
        MeditationSession(
            title = "Stress Zero",
            subtitle = "Gestion de crise",
            with = "Joël",
            category = MeditationCategory.RELAX,
            tags = listOf("Anxiété", "Urgence", "Respiration"),
            durationInMinutes = 5,
            isPremium = true
        ),
        MeditationSession(
            title = "Conte Lunaire",
            subtitle = "Pour s'évader",
            with = "Maël",
            category = MeditationCategory.SLEEP,
            tags = listOf("Histoire", "Imagination", "Enfants"),
            durationInMinutes = 35,
            isPremium = true
        ),
        MeditationSession(
            title = "Silence Intérieur",
            subtitle = "Préparation mentale",
            with = "Alain B.",
            category = MeditationCategory.FOCUS,
            tags = listOf("Silence", "Méditation", "Avancé"),
            durationInMinutes = 15,
            isPremium = false
        ),
        MeditationSession(
            title = "Gratitude du Soir",
            subtitle = "Pensées positives",
            with = "Houssem",
            category = MeditationCategory.RELAX,
            tags = listOf("Gratitude", "Soirée", "Bonheur"),
            durationInMinutes = 12,
            isPremium = false
        ),
        MeditationSession(
            title = "Espace Infini",
            subtitle = "Musique planante",
            with = "Éric",
            category = MeditationCategory.SLEEP,
            tags = listOf("Musique", "Cosmos", "Sommeil"),
            durationInMinutes = 55,
            isPremium = true
        ),
        MeditationSession(
            title = "Bruit du Vent",
            subtitle = "Nature pure",
            with = "Alain M.",
            category = MeditationCategory.RELAX,
            tags = listOf("Nature", "Vent", "Simplicité"),
            durationInMinutes = 20,
            isPremium = false
        ),
        MeditationSession(
            title = "Pomodoro Zen",
            subtitle = "Session de travail",
            with = "Aude",
            category = MeditationCategory.FOCUS,
            tags = listOf("Travail", "Timer", "Focus"),
            durationInMinutes = 25,
            isPremium = false
        ),
        MeditationSession(
            title = "Rêve Lucide",
            subtitle = "Guidance avancée",
            with = "Yannick",
            category = MeditationCategory.SLEEP,
            tags = listOf("Avancé", "Conscience", "Mystique"),
            durationInMinutes = 40,
            isPremium = true
        ),
        MeditationSession(
            title = "Pause Déjeuner",
            subtitle = "Reset mental",
            with = "Maël",
            category = MeditationCategory.RELAX,
            tags = listOf("Midi", "Digestion", "Calme"),
            durationInMinutes = 15,
            isPremium = false
        ),
        MeditationSession(
            title = "Vision Claire",
            subtitle = "Objectifs du jour",
            with = "Frédéric",
            category = MeditationCategory.FOCUS,
            tags = listOf("Visualisation", "Succès", "Matin"),
            durationInMinutes = 8,
            isPremium = false
        ),
        MeditationSession(
            title = "Feu de Cheminée",
            subtitle = "Chaleur et calme",
            with = "Joël",
            category = MeditationCategory.SLEEP,
            tags = listOf("Nature", "Foyer", "Hiver"),
            durationInMinutes = 45,
            isPremium = true
        ),
        MeditationSession(
            title = "Joie Simple",
            subtitle = "Sourire intérieur",
            with = "Alain B.",
            category = MeditationCategory.RELAX,
            tags = listOf("Joie", "Émotion", "Bienveillance"),
            durationInMinutes = 1,
            isPremium = false
        ),
        MeditationSession(
            title = "Fin de Journée",
            subtitle = "Bilan calme",
            with = "Houssem",
            category = MeditationCategory.FOCUS,
            tags = listOf("Bilan", "Organisation", "Soirée"),
            durationInMinutes = 15,
            isPremium = false
        )
    )
}

