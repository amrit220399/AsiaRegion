package com.apsinnovations.asiaregion.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apsinnovations.asiaregion.R;
import com.apsinnovations.asiaregion.models.AsianCountriesModel;
import com.apsinnovations.asiaregion.models.CountryLanguagesModel;
import com.apsinnovations.asiaregion.svg.SvgDecoder;
import com.apsinnovations.asiaregion.svg.SvgDrawableTranscoder;
import com.apsinnovations.asiaregion.svg.SvgSoftwareLayerSetter;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;
import com.google.gson.Gson;

import java.io.InputStream;
import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder> {

    private Context context;
    private  List<AsianCountriesModel> countries;

    public CountriesAdapter(Context context, List<AsianCountriesModel> countries) {
        this.context = context;
        this.countries = countries;
    }

    @NonNull
    @Override
    public CountriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountriesViewHolder(LayoutInflater.from(context).inflate(R.layout.card_countries,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesViewHolder holder, int position) {
        GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder = Glide.with(context)
                .using(Glide.buildStreamModelLoader(Uri.class, context), InputStream.class)
                .from(Uri.class)
                .as(SVG.class)
                .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                .listener(new SvgSoftwareLayerSetter<Uri>());

        requestBuilder.diskCacheStrategy(DiskCacheStrategy.NONE)
                .load(Uri.parse(countries.get(position).getFlag()))
                .into(holder.imgFlag);
        holder.txtName.setText(countries.get(position).getName());
        holder.txtCapital.setText(countries.get(position).getCapital());
        holder.txtRegion.setText(countries.get(position).getRegion());
        holder.txtSubregion.setText(countries.get(position).getSubregion());
        holder.txtPopulation.setText(String.valueOf(countries.get(position).getPopulation()));
        holder.txtBorders.setText(String.valueOf(countries.get(position).getBorders().size()));
        holder.txtLanguages.setText(String.valueOf(countries.get(position).getLanguages().size()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Country Info")
                        .setMessage("---Borders---\n"
                                + getBorders(position)
                                + "\n\n---Languages---\n"
                                + getLanguages(position))
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alertDialog =builder.create();
                alertDialog.show();
            }
        });
    }

    private String getBorders(int position){
        StringBuilder stringBuilder = new StringBuilder();
        for(String border : countries.get(position).getBorders()){
            stringBuilder.append(border).append(" ");
        }
        return stringBuilder.toString();
    }

    private String getLanguages(int position){
        StringBuilder stringBuilder = new StringBuilder();
        Gson gson=new Gson();
        for(CountryLanguagesModel language : countries.get(position).getLanguages()){
            stringBuilder.append(gson.toJson(language)).append("\n");
        }
        return  stringBuilder.toString();
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public static class CountriesViewHolder extends RecyclerView.ViewHolder{
        TextView txtName,txtCapital,txtRegion,txtSubregion,txtPopulation,txtBorders,
        txtLanguages;
        ImageView imgFlag;
        public CountriesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.name);
            txtCapital=itemView.findViewById(R.id.capital);
            txtRegion=itemView.findViewById(R.id.region);
            txtSubregion=itemView.findViewById(R.id.subregion);
            txtPopulation=itemView.findViewById(R.id.population);
            txtBorders=itemView.findViewById(R.id.borders);
            txtLanguages=itemView.findViewById(R.id.languages);
            imgFlag=itemView.findViewById(R.id.imgFlag);

        }
    }
}
