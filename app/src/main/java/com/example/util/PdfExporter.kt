package com.example.util

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import androidx.core.content.FileProvider
import com.example.model.CocktailRecipe
import com.example.model.StudentProfile
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object PdfExporter {

    fun exportRecipePdf(context: Context, recipe: CocktailRecipe, studentName: String): File? {
        val document = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create() // A4 standard (595 x 842 pt)
        val page = document.startPage(pageInfo)
        val canvas: Canvas = page.canvas

        val titlePaint = Paint().apply {
            color = Color.rgb(20, 24, 33)
            textSize = 24f
            typeface = Typeface.create(Typeface.SERIF, Typeface.BOLD)
            isAntiAlias = true
        }

        val subtitlePaint = Paint().apply {
            color = Color.rgb(180, 125, 29) // Amber gold
            textSize = 14f
            typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
            isAntiAlias = true
        }

        val headerPaint = Paint().apply {
            color = Color.rgb(40, 48, 65)
            textSize = 13f
            typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
            isAntiAlias = true
        }

        val bodyPaint = Paint().apply {
            color = Color.rgb(55, 65, 81)
            textSize = 11f
            typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL)
            isAntiAlias = true
        }

        val notePaint = Paint().apply {
            color = Color.rgb(110, 120, 135)
            textSize = 9.5f
            typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.ITALIC)
            isAntiAlias = true
        }

        val borderPaint = Paint().apply {
            color = Color.rgb(229, 169, 60)
            style = Paint.Style.STROKE
            strokeWidth = 2f
        }

        val accentBarPaint = Paint().apply {
            color = Color.rgb(229, 169, 60)
            style = Paint.Style.FILL
        }

        // Draw elegant border
        canvas.drawRect(25f, 25f, 570f, 817f, borderPaint)
        canvas.drawRect(28f, 28f, 567f, 814f, Paint().apply {
            color = Color.rgb(214, 115, 67)
            style = Paint.Style.STROKE
            strokeWidth = 0.75f
        })

        // Header Banner
        canvas.drawRect(30f, 30f, 565f, 100f, Paint().apply {
            color = Color.rgb(26, 31, 43)
            style = Paint.Style.FILL
        })
        val bannerTextPaint = Paint().apply {
            color = Color.rgb(245, 246, 249)
            textSize = 20f
            typeface = Typeface.create(Typeface.SERIF, Typeface.BOLD)
            isAntiAlias = true
        }
        canvas.drawText("BARS R US MIXOLOGY ACADEMY", 45f, 65f, bannerTextPaint)
        canvas.drawText("OFFICIAL COCKTAIL FORMULA & INTELLECTUAL PROPERTY RECORD", 45f, 85f, Paint().apply {
            color = Color.rgb(229, 169, 60)
            textSize = 10f
            typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
            isAntiAlias = true
        })

        var y = 135f

        // Drink Title
        canvas.drawText(recipe.name.uppercase(Locale.getDefault()), 45f, y, titlePaint)
        y += 20f
        val creatorTag = if (recipe.isCustom) "Original Custom Creation by $studentName" else "IBA Standard Mixology Record"
        canvas.drawText(creatorTag, 45f, y, subtitlePaint)
        y += 28f

        // Metadata grid
        val dateFormat = SimpleDateFormat("MMMM dd, yyyy - HH:mm", Locale.getDefault())
        canvas.drawText("Date Registered: ${dateFormat.format(Date(recipe.creationTimestamp))}", 45f, y, bodyPaint)
        canvas.drawText("Serving Glass: ${recipe.glass.displayName} (${recipe.glass.capacityOz} oz / ${recipe.glass.capacityMl} ml)", 310f, y, bodyPaint)
        y += 18f
        canvas.drawText("Technique: ${recipe.technique.actionName}", 45f, y, bodyPaint)
        canvas.drawText("Ice Requirement: ${if (recipe.iceRequired) "Served on the Rocks" else "Served Up (Chilled & Strained)"}", 310f, y, bodyPaint)
        y += 26f

        // Divider
        canvas.drawRect(45f, y, 550f, y + 2f, accentBarPaint)
        y += 22f

        // Ingredients Section
        canvas.drawText("MEASURED INGREDIENTS & PROPORTIONS", 45f, y, headerPaint)
        y += 18f

        var totalOz = 0.0f
        recipe.ingredients.forEach { item ->
            val bullet = "•   ${item.ingredient.name}"
            val measure = "${item.amountOz} ${item.ingredient.unit}"
            canvas.drawText(bullet, 60f, y, bodyPaint)
            canvas.drawText(measure, 380f, y, headerPaint)
            val abvText = if (item.ingredient.abv > 0) "(${item.ingredient.abv}% ABV)" else "(Non-alcoholic mixer)"
            canvas.drawText(abvText, 450f, y, notePaint)
            totalOz += item.amountOz
            y += 18f
        }

        y += 10f
        canvas.drawText("Total Fluid Volume: ${String.format(Locale.US, "%.2f oz (~%.0f ml)", totalOz, totalOz * 29.5735f)}", 60f, y, subtitlePaint)
        y += 26f

        // Garnish Section
        canvas.drawText("GARNISH & PRESENTATION", 45f, y, headerPaint)
        y += 18f
        canvas.drawText("•   ${recipe.garnish}", 60f, y, bodyPaint)
        y += 26f

        // Method
        canvas.drawText("PREPARATION METHOD", 45f, y, headerPaint)
        y += 18f
        val prepText = "1. Fill ${if (recipe.technique == com.example.model.PrepTechnique.SHAKEN) "Boston shaker" else "mixing glass"} with fresh ice.\n" +
                "2. Measure all ingredients using an accurate Japanese-style jigger.\n" +
                "3. ${recipe.technique.toolDescription}.\n" +
                "4. Strain into pre-chilled ${recipe.glass.displayName}."
        prepText.split("\n").forEach { line ->
            canvas.drawText(line, 60f, y, bodyPaint)
            y += 16f
        }
        y += 15f

        // Flavor & Tasting Profile
        canvas.drawText("TASTING NOTES & CHARACTER", 45f, y, headerPaint)
        y += 18f
        canvas.drawText(recipe.description, 60f, y, bodyPaint)
        y += 30f

        // Legal & Copyright Notice Box
        canvas.drawRect(45f, y, 550f, y + 105f, Paint().apply {
            color = Color.rgb(248, 249, 252)
            style = Paint.Style.FILL
        })
        canvas.drawRect(45f, y, 550f, y + 105f, Paint().apply {
            color = Color.rgb(209, 213, 219)
            style = Paint.Style.STROKE
            strokeWidth = 1f
        })

        canvas.drawText("INTELLECTUAL PROPERTY & COPYRIGHT NOTICE FOR BARTENDERS", 55f, y + 20f, headerPaint)
        val legal1 = "Under US and international copyright law, functional lists of cocktail ingredients are not copyrightable,"
        val legal2 = "however, original creative recipe narratives, signature drink names (via USPTO Trademark), and promotional"
        val legal3 = "presentation can be legally protected. This document provides formal timestamped provenance of authorship."
        canvas.drawText(legal1, 55f, y + 40f, notePaint)
        canvas.drawText(legal2, 55f, y + 54f, notePaint)
        canvas.drawText(legal3, 55f, y + 68f, notePaint)
        canvas.drawText("Registry Signature: Bars R Us Cryptographic Stamp #${recipe.id.hashCode()}", 55f, y + 90f, subtitlePaint)

        // Footer
        canvas.drawText("Bars R Us Bartending Academy - Designed for ServSafe Certified Mixologists", 110f, 800f, notePaint)

        document.finishPage(page)

        return try {
            val file = File(context.cacheDir, "BarsRUs_Recipe_${recipe.id}_${System.currentTimeMillis()}.pdf")
            val outputStream = FileOutputStream(file)
            document.writeTo(outputStream)
            outputStream.close()
            document.close()
            file
        } catch (e: Exception) {
            e.printStackTrace()
            document.close()
            null
        }
    }

    fun exportStudentTranscriptPdf(context: Context, profile: StudentProfile): File? {
        val document = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = document.startPage(pageInfo)
        val canvas: Canvas = page.canvas

        val titlePaint = Paint().apply {
            color = Color.rgb(20, 24, 33)
            textSize = 22f
            typeface = Typeface.create(Typeface.SERIF, Typeface.BOLD)
            isAntiAlias = true
        }

        val subtitlePaint = Paint().apply {
            color = Color.rgb(180, 125, 29)
            textSize = 14f
            typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
            isAntiAlias = true
        }

        val headerPaint = Paint().apply {
            color = Color.rgb(40, 48, 65)
            textSize = 13f
            typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
            isAntiAlias = true
        }

        val bodyPaint = Paint().apply {
            color = Color.rgb(55, 65, 81)
            textSize = 11f
            typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL)
            isAntiAlias = true
        }

        val borderPaint = Paint().apply {
            color = Color.rgb(229, 169, 60)
            style = Paint.Style.STROKE
            strokeWidth = 2.5f
        }

        canvas.drawRect(25f, 25f, 570f, 817f, borderPaint)

        // Banner
        canvas.drawRect(30f, 30f, 565f, 95f, Paint().apply {
            color = Color.rgb(20, 24, 33)
            style = Paint.Style.FILL
        })
        canvas.drawText("BARS R US MIXOLOGY ACADEMY", 45f, 62f, Paint().apply {
            color = Color.rgb(245, 246, 249)
            textSize = 18f
            typeface = Typeface.create(Typeface.SERIF, Typeface.BOLD)
            isAntiAlias = true
        })
        canvas.drawText("STUDENT ACHIEVEMENT & MIXOLOGY ACADEMIC TRANSCRIPT", 45f, 82f, Paint().apply {
            color = Color.rgb(229, 169, 60)
            textSize = 9.5f
            typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
            isAntiAlias = true
        })

        var y = 130f
        canvas.drawText("STUDENT: ${profile.studentName.uppercase(Locale.getDefault())}", 45f, y, titlePaint)
        y += 22f
        canvas.drawText("Status: Level ${profile.level} Mixologist • Total XP: ${profile.xp} pts", 45f, y, subtitlePaint)
        y += 30f

        // Stats Box
        canvas.drawRect(45f, y, 550f, y + 120f, Paint().apply {
            color = Color.rgb(246, 248, 252)
            style = Paint.Style.FILL
        })
        canvas.drawText("CORE COMPETENCIES & PERFORMANCE METRICS", 60f, y + 25f, headerPaint)
        canvas.drawText("•   Cocktail Ratio Precision Accuracy: ${String.format(Locale.US, "%.1f%%", profile.ratioAccuracyScore)}", 65f, y + 50f, bodyPaint)
        canvas.drawText("•   Academy Quizzes Passed: ${profile.totalQuizzesPassed} Modules", 65f, y + 70f, bodyPaint)
        canvas.drawText("•   Speed Rush Bar Peak Score: ${profile.speedRushHighScore} points", 65f, y + 90f, bodyPaint)
        canvas.drawText("•   ServSafe Alcohol Awareness & Law: ${if (profile.servSafeQuizPassed) "CERTIFIED PASSED" else "In Progress"}", 65f, y + 110f, subtitlePaint)
        y += 145f

        // Badges section
        canvas.drawText("EARNED CREDENTIALS & MASTERY BADGES", 45f, y, headerPaint)
        y += 20f
        profile.unlockedBadges.forEach { badge ->
            val status = if (badge.unlocked) "[MASTERED]" else "[IN TRAINING]"
            canvas.drawText("${badge.iconEmoji}  ${badge.title} - $status", 60f, y, bodyPaint)
            y += 16f
            canvas.drawText("     ${badge.description}", 60f, y, Paint().apply {
                color = Color.rgb(107, 114, 128)
                textSize = 9.5f
                typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.ITALIC)
            })
            y += 20f
        }

        y += 25f
        canvas.drawText("RESPONSIBLE BEVERAGE SERVICE (RBS) ATTESTATION", 45f, y, headerPaint)
        y += 18f
        val rbsText1 = "The candidate has completed curriculum covering Blood Alcohol Concentration (BAC) limits, standard drink"
        val rbsText2 = "volume equivalency (14g pure ethanol), minor fake ID detection, Dram Shop liability laws, and protocol"
        val rbsText3 = "for ethical, firm refusal of service to intoxicated patrons."
        canvas.drawText(rbsText1, 60f, y, bodyPaint)
        y += 16f
        canvas.drawText(rbsText2, 60f, y, bodyPaint)
        y += 16f
        canvas.drawText(rbsText3, 60f, y, bodyPaint)

        canvas.drawText("Official Bars R Us Academy Seal", 200f, 790f, subtitlePaint)

        document.finishPage(page)

        return try {
            val file = File(context.cacheDir, "BarsRUs_Transcript_${System.currentTimeMillis()}.pdf")
            val outputStream = FileOutputStream(file)
            document.writeTo(outputStream)
            outputStream.close()
            document.close()
            file
        } catch (e: Exception) {
            e.printStackTrace()
            document.close()
            null
        }
    }

    fun sharePdf(context: Context, pdfFile: File, title: String) {
        val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", pdfFile)
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "application/pdf"
            putExtra(Intent.EXTRA_STREAM, uri)
            putExtra(Intent.EXTRA_SUBJECT, title)
            putExtra(Intent.EXTRA_TEXT, "Here is my official Bars R Us Bartending Academy document: $title")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(intent, "Share via..."))
    }
}
